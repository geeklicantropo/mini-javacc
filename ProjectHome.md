Projeto de um compilador para a linguagem MiniJava

**Equipe:**
  1. Bruno Anderson
  1. Carlos Átila
  1. Leonardo Cardoso
  1. Pedro Crispim

**Professor:** Francisco Heron de Carvalho Junior


**Objetivo:**

Implementar um compilador para a linguagem MiniJava seguindo as etapas descritas no livro 'Modern Compiler Implementation in Java'.


ANÁLISE LÉXICA:

> Foi utilizado o gerador de código javacc, que apartir de um código-fonte especificando as regras para formação de tokens, produziu as classes java.

ANÁLISE SINTÁTICA:

> O arquivo javacc foi estendido para tratar as produções, identificar que reduções são realizadas e retornar se é um programa válido para a linguagem especificada.

ÁRVORE SINTÁTICA ABSTRATA

> Novamente o código foi estendido para executar ações semânticas ao realizar uma redução na análise sintática. Oobjetos de classes especificadas pelos recursos disponíveis no site do livro-texto são utilizados para construir uma árvore abstrata de derivações, indepedente de detalhes de mais baixo nível como ambiguidade ou recursão a esquerda.

TABELA DE SÍMBOLOS

> Foi utilizado o padrão de projeto visitor para percorrer a árvore abstrata gerada e assim construir uma tabela imperativa que guarda informação dos tipos das variáveis, classes, métodos declarados e seus respectivos escopos.

VERIFICAÇÃO DE TIPOS

> Com a tabela de símbolos construida, outra classe visitor percorre a árvore checando se as informações de tipos obtidas no programa estão de acordo com as informações armazenadas na tabela.