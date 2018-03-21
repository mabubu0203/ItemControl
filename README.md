ItemControl
====

"商品コード"と"商品"を管理します。  

## 起動方法

1. 当プロジェクトをIntelliJ IDEAにGradleProjectとしてclone  
2. docker-composeよりmysqlを起動  
    `$ cd docker`  
    `$ docker-compose -f docker-compose.yml build`  
    `$ docker-compose -f docker-compose.yml up -d`  
3. GradleタスクよりbootRun(`:micro-api -> Tasks -> application -> bootRun`)

## 終了方法

1. bootRunの停止  
2. docker-composeより停止  
    `$ cd docker`  
    `$ docker-compose -f docker-compose.yml stop`
    
## Project

Gradle のマルチプロジェクト構成をとっています。

```
ItemControl
- config                              … build.gradleから使用するTool群の設定ファイル格納フォルダ
- docker                              … docker-compose格納フォルダ
- - docker-compose.yaml               … mysql起動ファイル
- micro-api                           … RestApi起動プロジェクト
- micro-common                        … 共通ライブラリプロジェクト
- micro-interfaces                    … RestApiのI/Oを定義したプロジェクト
- micro-rdb                           … Databaseアクセスプロジェクト
- micro-test                          … Testツールを格納したプロジェクト
- build.gradle                        … Gradle プロジェクト定義
- settings.gradle                     … Gradle プロジェクト設定
```    

## Endpoints

[Grafana][]  
[Prometheus][]  
[ItemControl][]  

## Middleware

| name              | version
| :---------------- | :-------
| OracleJdk         | 1.9
| DockerCompose     | 1.18
| MySql             | 5.7.x
| Gradle            | 4.6 
| SonarQube         | 7.0

## Library

| name               | version
| :----------------- | :------
| SpringBoot         | 2.0.x
| SpringData-jpa     | 2.0.x
| SpringFox          | 2.x
| Lombok             | 1.x

## End
[Grafana]: http://localhost:3000        "Grafana"
[Prometheus]: http://localhost:9090     "Prometheus"
[ItemControl]: http://localhost:8085/swagger-ui.html     "ItemControl"