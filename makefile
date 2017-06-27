#!/usr/bin/make
classPath := xyz/davidChangx/algorithms
mathPath := $(classPath)/math
.PHONY:default
default:operator basic $(classPath)/equation/SecantRoot.class
.PHONY:operator
triangle := Cosine Sine Tangent
arcTriangle := $(addprefix Arc,$(triangle))
hyperbolicTriangle := $(addprefix Hyperbolic,$(triangle))
operatorClass := $(triangle) $(arcTriangle) $(hyperbolicTriangle) Lg Ln Exponential Plus Minus Module Multiply Divide
operator:$(mathPath)/operator/Operator.class
	javac -d . $(addsuffix .java,$(operatorClass))
.PHONY:basic
basic:$(mathPath)/Expression.class
$(mathPath)/Expression.class:$(mathPath)/operator/Operator.class $(mathPath)/Operand.class $(mathPath)/Unknown.class $(mathPath)/ExpressionItem.class $(classPath)/Function.class $(mathPath)/operator/OperatorGroupMode.class
	javac -d . Expression.java
$(classPath)/equation/SecantRoot.class:$(classPath)/Function.class
	javac -d . SecantRoot.java
$(mathPath)/operator/Operator.class:$(mathPath)/ExpressionItem.class $(mathPath)/operator/OperatorGroupMode.class
	javac -d . Operator.java
$(mathPath)/Operand.class:$(mathPath)/ExpressionItem.class
	javac -d . Operand.java
$(mathPath)/Unknown.class:$(mathPath)/ExpressionItem.class
	javac -d . Unknown.java
$(mathPath)/ExpressionItem.class:
	javac -d . ExpressionItem.java
$(classPath)/Function.class:
	javac -d . Function.java
$(mathPath)/operator/OperatorGroupMode.class:
	javac -d . OperatorGroupMode.java
.PHONY:clean
clean:
	-rm -r xyz 
	-rm *.class
