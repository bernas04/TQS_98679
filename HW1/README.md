# HW1: Mid-term assignment

## Develop a multi-layer web application, in Spring Boot, supplied with automated tests

### Architecture

![HW1 architecture](/HW1/schemas/hw1.png)

### Tecnologies used

| **Component** 	| **Tecnology**       	|
|---------------	|---------------------	|
| API           	| [COVID-19 Statistics](https://rapidapi.com/axisbits-axisbits-default/api/covid-19-statistics/)|
| backend       	| Springboot          	|
| frontend      	| HTML CSS JavaScript   |

### Demo Gif
![Demo](/HW1/schemas/hw1_demo.gif)


### Set up application
```bash
# bin/bash
# in HW1 folder
docker-compose up
```


### Set up tests

```bash
# bin/bash
docker-compose up # you need to have the app running to selenium tests
cd api/HW1/
mvn clean test
```

The api documentation is present [here](https://documenter.getpostman.com/view/18307740/UyrGCExg)

TQS 2022
