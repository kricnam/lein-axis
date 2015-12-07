# lein-axis2
Simple Leiningen plug-in to speed up working with Apache Axis2 and WSDL files

## Usage
Use Apache Axis2 to generate Java classes from WSDL files.

You will need to add Apache Axis2 to your project as a dependency, e.g.:

    :dependencies [[org.clojure/clojure "1.7.0"]
                   [org.apache.axis2/axis2 "1.6.3"]]

Also, this plug-in should be added to your project as a development dependency:

    :profiles {:dev {:dependencies [[lein-axis "0.1.0"]]}}

Then, to configure what WSDL files to use and where to put the generated
source files:

    :plugins [[lein-axis2 "0.1.0"]]
    :axis2-gen-root "./"
    :axis2-gen-java "src/java"
    :axis2-gen-meta "src/resources"
    :axis [["src/wsdl/myservice.wsdl" "generated.myservice"]
    	   ["src/wsdl/myotherservice.wsdl" "generated.myotherservice"]]

Then, simply run:

    $ lein deps
    $ lein axis2

lein-axis2 uses this as default setting to save the generated code.


If you're generating server-side classes, or need to add extra arguments,
you can do this per-WSDL file, like so:

    :axis2 ["src/wsdl/myservice.wsdl" "generated.myservice" ["-ss"]]

the detailed params on genrating code, please refer:
http://axis.apache.org/axis2/java/core/docs/reference.html

## License

Distributed under the Eclipse Public License, the same as Clojure.
