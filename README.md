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
4. GradleタスクよりbootRun(`:micro-admin -> Tasks -> application -> bootRun`)

## 停止方法

1. bootRunの停止  
2. docker-composeより停止  
    `$ cd docker`  
    `$ docker-compose -f docker-compose.yml stop`
    
## 構成

Gradleのマルチプロジェクト構成をとっています。

```
ItemControl                           … rootプロジェクト
- config                              … build.gradleから使用するTool群の設定ファイル格納フォルダ
- docker                              … docker-compose格納フォルダ
- - grafana                           … 
- - mysql                             … 
- - prometheus                        … 
- - sonarqube                         … 
- - docker-compose.yaml               … dockerコンテナ起動ファイル
- micro-admin                         … adminプロジェクト
- micro-api                           … RestApiプロジェクト
- micro-common                        … 共通ライブラリプロジェクト
- micro-interfaces                    … RestApiのI/Oを定義したプロジェクト
- micro-rdb                           … RDBアクセスプロジェクト
- micro-test                          … Testツールを格納したプロジェクト
- micro-validator                     … 検証プロジェクト
- build.gradle                        … Gradle プロジェクト定義
- gradle.properties                   … Gradle プロジェクト設定
- settings.gradle                     … Gradle プロジェクト設定
```    

## Endpoints

[Grafana][]  
[Prometheus][]  
[SonarQube][]  
[Admin][]  
[ItemControl][]  

## 環境

### Middleware

| name              | version
| :---------------- | :-------
| OracleJdk         | 1.9
| DockerCompose     | 1.x
| MySql             | 5.7.x
| Gradle            | 4.8.1 
| SonarQube         | 7.x
| Grafana           | 5.x
| Prometheus        | 2.x

### Library

| name               | version
| :----------------- | :------
| SpringBoot         | 2.0.x
| SpringData-jpa     | 2.0.x
| SpringFox          | 2.x
| Lombok             | 1.x

[Grafana]: http://localhost:3000/      "Grafana"
[Prometheus]: http://localhost:9090/     "Prometheus"
[SonarQube]: http://localhost:9000/sonar/     "SonarQube"
[Admin]: http://localhost:8095/admin     "Admin"
[ItemControl]: http://localhost:8085/swagger-ui.html     "ItemControl"