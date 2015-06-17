# **Análise Léxica** #

A principal função do Analisador Léxico é de fragmentar o programa fonte em componentes básicos, chamados de átomos ou tokens. Do ponto de vista de implementação do compilador, o analisador léxico atua como uma interface entre o analisador sintático e o texto de entrada, convertendo a seqüência de caracteres que formam o programa fonte em uma seqüência de tokens que o analisador sintático consumirá.

O analisador léxico executa usualmente uma série de funções, que são de grande importância para a correta operação das outras partes do compilador. As funções mais importantes do analisador léxico são:


&lt;UL&gt;


> 

&lt;LI&gt;

Extração e Classificação dos tokens: extração e classificação dos tokens que compõem o texto do programa fonte. Entre a classe de tokens mais encontradas em analisadores léxicos estão: identificadores, palavras reservadas, números inteiros, cadeias de caracteres, caracteres especiais simples e compostos.

&lt;/LI&gt;


> 

&lt;LI&gt;

Eliminação de Delimitadores e Comentários: espaços em branco, símbolos separadores e comentários são irrelevantes na geração de código podendo ser eliminados pelo analisador léxico.

&lt;/LI&gt;


> 

&lt;LI&gt;

Recuperação de Erros: detecção de cadeias de caracteres que não  obedecem a nenhuma lei de formação das classes de tokens que o analisador léxico reconhece.

&lt;/LI&gt;




&lt;/UL&gt;



Nesta etapa, toda nossa programação concentrou-se no arquivo MiniJava.jj onde foi declarado a Gramática e as produções correspondentes a Gramática. Através do JavaCC foram criados os tokens para uso posterior do Analisador Sintático, vide MiniJavaParserConstants.java.