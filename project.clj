(defproject lein-axis2 "0.1.0"
  :description "Simple Leiningen plug-in to speed up working with Apache Axis2 and WSDL files."
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.apache.axis2/axis2-codegen "1.6.3" :exclusions [org.apache.geronimo.specs/geronimo-activation_1.1_spec]]
                 ]
 
  :eval-in-leiningen true
  )
