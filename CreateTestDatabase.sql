--CreateDatabase.sql
connect 'jdbc:derby:operator;create=true';
create table Operator
	(operator varchar(10) constraint consOperator not null primary key,
	operatorClass varchar(30) constraint consClass not null unique,
	inStackPriority smallint default null,
	outStackPriority smallint default null,
	operandCount smallint constraint consOperand not null);
insert into Operator (operator,operatorClass,inStackPriority,outStackPriority,operandCount) values
	('+','Plus',5,5,2),('-','Minus',5,5,2),
	('*','Multiply',8,8,2),('/','Divide',8,8,2),
	('mod','Module',6,6,2),
	('^','Power',9,9,2),
	('$','Head',null,1,0),('#','Tail',0,null,0),
	(')','RightBracket',0,null,0),('(','LeftBracket',15,1,0),
	('sin(','Sine',15,1,1),('cos(','Cosine',15,1,1),('tan(','Tangent',15,1,1),
	('e^(','Exponential',15,1,1),
	('sinh(','HyperbolicSine',15,1,1),('cosh(','HyperbolicCosine',15,1,1),('tanh','HyperbolicTangent',15,1,1),
	('arcsin(','ArcSine',15,1,1),('arccos(','ArcCosine',15,1,1),('arctan(','ArcTangent',15,1,1),
	('ln(','Ln',15,1,1),'lg(','Lg',15,1,1);
