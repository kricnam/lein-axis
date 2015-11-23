(ns leiningen.axis2
  (:use [clojure.java.shell :only [sh]]
	[leiningen.classpath :only [get-classpath-string]]))

(def ^:const WSDL2Java-class "org.apache.axis2.wsdl.WSDL2Java")

(defn- cmd
  "Convert the [wsdl target-package] vector from project.clj to a WSDL2Java call."
  [p [wsdl package extra]]
   
  (concat 
   ["java" "-cp" (get-classpath-string p) WSDL2Java-class
    "-o" (get p :generated-code-root "./")
    "-S" (get p :generated-source-path "src/java")
    "-R" (get p :generated-res-path "src/resources")
    "-p" package
    extra
    "-uri" wsdl]
   )
)

(defn axis2
  "Use Apache Axis2 to generate Java classes for WSDL files.

You will need to add Apache Axis2 to your project as a dependency, e.g.:
...
  :dependencies [[org.clojure/clojure \"1.7.0\"]
		 [org.apache.axis2/axis2 \"1.6.3\"]]

Then, to configure what WSDL files to use and where to put the generated
source files:
...
  :generated-code-root \"./\"
  :generated-source-path \"src/java\"
  :generated-res-path \"src/resources\"
  :axis2 [[\"src/wsdl/myservice.wsdl\" \"generated.myservice\"]
	 [\"src/wsdl/myotherservice.wsdl\" \"generated.myotherservice\"]]

lein-axis2 default save generated files in the src directory (for the
root of the generated packages). 
"
  [project & rest]
  

   (doseq [cmd-out (map (comp (partial apply sh)
			     (partial cmd project))
		       (:axis2 project))]
    (println (:err cmd-out)))
)

