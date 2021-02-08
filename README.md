# Real estate web scraping


## Description

Due to he complexity in real estate search in Paris, the idea is to use web scraping.
Web sites targets are :  
* [se loger](https://www.seloger.com/) 
* [PAP](https://www.pap.fr/)
* [le bon coin](https://www.leboncoin.fr/)
* [espaces atypiques](https://www.espaces-atypiques.com/)
* [le figaro immobilier](https://immobilier.lefigaro.fr/)


## Tools

* JAVA 11
* [Jsoup](https://jsoup.org/)
* Spring boot batch

### init project

```sh
curl https://start.spring.io/starter.zip -d dependencies=devtools,batch,cloud-task,mysql,hsql,lombok \
           -d bootVersion=2.4.2 -d applicationName=immo-scraping -d baseDir=immo-scraping \
           -d javaVersion=11 -d type=maven-project -d packaging=jar -d groupId=com.ksat.scraping.immo \
           -d artifactId=immo-scraping -d name=immo-scraping \
           -d description="web scrapping for real estate research" \
           -o immo-scraping.zip
```

### add web scraping dependency

```xml
<!-- our scraping library -->
<dependency>
    <groupId>org.jsoup</groupId>
    <artifactId>jsoup</artifactId>
    <version>1.13.1</version>
</dependency>
```

## Web Site

### Se Loger

#### Searching Url

```
https://www.seloger.com/list.htm?projects=2&types=1,2&natures=1,2,4&places=%5b%7b%22inseeCodes%22:%5b750056%5d%7d%5d&proximities=0,10&price=NaN/550000&surface=55/NaN&bedrooms=2,3,4,5&rooms=3,4,5&enterprise=0&qsVersion=1.0&m=search_refine
```

### PAP

#### Searching Url

```
https://www.pap.fr/annonce/vente-appartement-maison-paris-75-g439g22448g43303g43308g43313g43314g43321g43335g43339g43350g43705-a-partir-du-3-pieces-a-partir-de-2-chambres-jusqu-a-550000-euros-a-partir-de-55-m2-3
```

### Le Bon Coin

#### Searching Url

```
https://www.leboncoin.fr/recherche?category=9&text=paris&locations=Paris__48.85790600716752_2.3588440688099768_10000_5000&real_estate_type=1%2C2&immo_sell_type=old%2Cnew&price=min-550000&square=50-max&rooms=3-max
```

## Documentation

* [Baeldung : Testing a Spring Batch Job](https://www.baeldung.com/spring-batch-testing-job)
* [Testing spring batch](https://docs.spring.io/spring-batch/docs/current/reference/html/testing.html)
* [JSoup](https://jsoup.org/)
* [Spring boot batch](https://spring.io/guides/gs/batch-processing/)