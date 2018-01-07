# Lista de Cidade #

Esse sistema tem como objetivo permitir ao usuário fazer buscas em uma
lista de cidades cadastradas. Desse modo, o usuário pode obter a
pontuação da cidade que deseja

## Framework e Arquitetura ##

Para o desenvolvimento dessse sistema, foi utiizado o framework Spring
Boot (1.5.9).
A arquitetura utilizada foi o MVC (Model-View-Controller), implementada
utilizando o Spring MVC e o engine de template Thymeleaf para a geração
das views.
A seguir encontra-se uma descrição de cada camada e como ela se encontra
implementada no projeto.

### A camada Model (M) ###
A camada Model (M) é representada pelos pacotes:
  * br.com.edeploy.listacidades.domain
  * br.com.edeploy.listacidades.dao (interfaces e implementações das mesmas)
  * br.com.edeploy.listacidades.service

O pacote domain contém entidades, Data Transfer Objects (DTOs), e interfaces.
As entidades são utilizadas para receber e/ou transferir dados para a
base de dados. Nesse sistema, a base de dados deve ser entendida como o
webservice que fornece a lista de cidades e a pontuação. As interfaces
permitem que sejam expostos dados diferentes de acordo com o contexto.
Nesse sistema, só foi necessária uma interface, já que todas as
informações da entity eram necessárias. Por último, o DTOs são as
implementações dessas interfaces, e são utilizados pelos controllers
para passar informações para as views, sem expor diretamente as entities.

O pacote dao contém as interfaces e suas implementações dos DAOs (Data
Access Object), que são classes utilizadas para se comunicarem com a
base de dados. Elas são responsáveis por criar, recuperar, atualizar e
remover registros da base de dados.

O pacote service contém as interfaces e as implementações dos services.
Os services devem ser entendidos como a implementação das regras de
negócio da aplicação. Eles são chamados pelos controllers, e em grande
parte das vezes se comunicam com as implementações dos DAOs para
interagirem com a base de dados. Outra opção, é o service se comunicar
com um ou mais services. Após realizar sua operação, o service
fornece um retorno para o controller.

Com isso, é possível perceber que nessa camada fica encapsulada toda a
informação e regras de negócio da aplicação, com funções bem definidas
para cada tipo de classe da camada.

### A camada Controller (C) ###
A camada Controller (C) é representada pelos pacote
br.com.edeploy.listacidades.controller

Nesse pacote estão os controllers, que respondem aos endpoints (endereços
que o usuário acessa). Eles são responsáveis por receber os dados que
o usuário informa, e chamar o(s) service(s) adequado(s) para atender a
solicitação do usuário. Além disso, são responsáveis por formatar a
resposta do service para o modo solicitado pelo usuário (como por
exemplo JSON) e tratar erros, exibindo mensagens explicativas, por
exemplo.

Os controllers também são responsáveis por cuidar da permissão de acesso
do usuário. Eles podem repassar as informações para as views, para que
essas cuidem da apresentação dos dados para o usuário, ou gerar
respostas diretamente para o usuário (JSON ou XML por exemplo).

### A camdada View (V) ###
A camada view tem como
função ser a interface do sistema para o usuário. Desse modo, as páginas
que o usuário acessa fazem parte dessa camada. Por meio delas o usuário
consegue informar dados, fazer buscas, remover informações, entre outras
possibilidades.
Nesse projeto, a camada view foi implementada utilizando o engine de
template Thymeleaf para a geração dos htmls, os quais estão presentes em
main/resources/templates. Além disso, foi utilizado o BootStrap (3.3.7),
uma biblioteca bem interessante, que fornece classes css e javascripts
para proporcionar sites mais responsivos, e visualmente agradáveis.
Também foi utilizada a biblioteca javascript Jquery (3.1.1), para
permitir chamadas AJAX.

## Como rodar o sistema? ##

Para rodar o sistema, basta ter o Java 8 instalado, e rodar o comando a
seguir dentro da raiz do projeto:

**java -jar build/libs/listacidades-1.0.1.jar**

## Possibilidades de melhoria ##

Utilizar Datatables (https://datatables.net/) para facilitar a paginação
e ordenação no próprio navegador, sem necessidade de fazer uma nova
chamada.

