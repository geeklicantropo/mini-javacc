# **Análise Sintática** #

É a fase da compilação responsável por determinar se uma dada cadeia de entrada pertence ou não à linguagem definida por uma gramática, gramática esta definida no arquivo MiniJava.jj.
A análise sintática, ou parsing, é o núcleo principal do front-end de um compilador. O parser é o responsável pelo controle de fluxo do
compilador através do código fonte em compilação. É ele quem ativa o léxico para que este lhe retorne os tokens, citados acima, e também ativa a análise semântica e a geração de código.
O resultado de seu processamento produz uma estrutura conhecidamente denominada AST – Abstract Syntax Tree. Esta árvore não precisa necessariamente ser gerada "fisicamente".

Nesta etapa de desenvolvimento, com o uso do JavaCC, temos gerado todos os Scanner e Parser de nosso compilador, tendo sido gerados os arquivos abaixo:

MiniJavaParser.java<br>
MiniJavaParserConstants.java<br>
MiniJavaParserTokenManager.java<br>
ParserException.java<br>
SimpleCharStream.java<br>
Token.java<br>
TokenMgrError.java<br>
MiniJava.jj