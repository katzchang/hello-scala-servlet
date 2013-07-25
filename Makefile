
all: test

test:
	./sbt test

test-continuous:
	./sbt ~test

jetty-run:
	@echo to test: curl '"http://localhost:8080/hello?hoge=fuga&hoge=piyo&foo=bar"'
	./mvn jetty:run

package:
	./mvn package

idea:
	./sbt gen-idea

eclipse:
	./sbt eclipse
