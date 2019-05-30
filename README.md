# MDI_SmartDoodleJPA

Le but de ce TP était de créer une version simplifiée de SmartDoodle, une alternative à l'outil [Doodle](https://www.doodle.com), proposant quelques fonctionnalités supplémentaires définies dans le sujet du TP.

A l'aide de Spring Boot, nous avons commencé par définir l'API permettant au front de manipuler les données stockées dans la base MySQL.
Il s'agissait alors de comprendre l'architecture applicable à ce genre de projet pour faire fonctionner le tout correctement.
Nous avons utilisé, dans la modélisation des entités, les annotations liées à la persistence des données @ManyToMany, @ManyToOne, etc.

Durant la séance de TP, nous avons également réussi à substituer la base locale MySQL par celle hébergée par l'ISTIC, en introduisant les logins et l'URL de celle-ci dans les fichiers de configuration du projet.

Finalement, ayant des soucis d'environnement nous avons refait le projet en s'aidant du modèle proposé par ce [guide](https://hellokoding.com/full-stack-crud-web-app-and-restful-apis-web-services-example-with-spring-boot-jpa-hibernate-mysql-vuejs-and-docker/), dont le code source ce trouve [ici](https://github.com/hellokoding/hellokoding-courses/tree/master/springboot-examples/springboot-crud-mysql-vuejs).

Nous avons réussi à produire une API RestFUL proposant toutes les opérations CRUD pour les trois entités Poll, Participant et Slot qui représentent respectivement un sondage, un participant ainsi qu'un choix de plage horaire dans un sondage.

Le front quant à lui, par manque de temps, n'offre que les opérations CRUD pour Poll et Participant, sans permettre d'ajouter un Participant à un Poll, ni de gérer les votes associés, mais ce TP nous a tout de même permis de visualiser et d'expérimenter Vue.JS avec également le côté consommation d'API.

### Pré-requis

N'ayant pas eu le temps de conteneriser l'applicatif Vue.JS, voici les pré-requis :

- Node.JS
- npm
- docker-compose

### Manipulation
```
git clone https://github.com/Theomariee/MDI_SmartDoodleJPA
cd MDI_SmartDoodleJPA
# Lance la partie back-end Spring + MySQL
docker-compose up

# Lance la partie frontend Vue.JS
cd frontend
npm i
npm run serve
```

Accèder à l'applicatif via `http://localhost:3000`
