# PANDA Application(Back end - Rest api)



## 개발동기 

**디지털 라이프스타일 속 사람과 사람이 주고받는 메시지를 소중히 여기고 기억에 남을 감동적인 이벤트를 선물하고 싶은 소비자를 위한 서비스를 개발하고 싶었다**.

**셀럽에게 누군가를 위한 메시지를 받는 건 매우 특별한 일이다.**

**그래서 셀럽에게 축하를 받아 자신이 사랑하는 혹은 친애하는 사람에게 잊지 못할 추억을 만들어 줄 수 있는 서비스를 계획하게 됐다.**



## 어플 시나리오

![image-20210705182758594](https://user-images.githubusercontent.com/67422547/124547479-280efa00-de67-11eb-9f09-6ab4ac64f281.png)

1. **유저가 셀럽에게 선물받는 사람의 이름 이메일 원하는 요구사항이(EX OO의 이름을 부르며 생일을 축하해 주세요) 담긴 영상 메시지를 요청한다.**

2. **셀럽은 요청사항에 따라 영상 메시지를 제작하고 선물 받는 이에게 영상을 어플 내에서 볼 수 있는 코드를 발송해 준다.**

3. **선물 받는 사람이 수신된 코드를 통해 영상을 확인한다. **



## 시연영상

![image-20210706143109353](https://user-images.githubusercontent.com/67422547/124547487-2d6c4480-de67-11eb-96c8-d6fc48b207fd.png)





https://user-images.githubusercontent.com/67422547/124547689-7ae8b180-de67-11eb-886a-b85ed308493e.mp4



## 서버 구상도

![image-20210705182012957](https://user-images.githubusercontent.com/67422547/124547500-32c98f00-de67-11eb-9d7f-e4b0b886761b.png)

**어플리케이션을 만들기 위해 하이브리드 앱 프레임워크로 React-native를 사용했습니다 **

**서버에 데이터들을 어플리케이션에서 사용하기위해 spring boot를 이용하여 rest api를 구현했습니다. **

**서버를 구축하기 위해 aws를 사용하였습니다. **

**첫번째로 서버를 만들기 위해 ec2인스턴스를 생성하였습니다. **

**두번째로 RDS입니다.**

**RDS는 EC2와 함께 사용하며 아마존에서 DB의 설정, 운영, 백업 등의 기능을 편하게 이용할 수 있게 해주는 EC2와 분리된 DB전용서버이다. 데이터베이스의 종류로는 mariaDB를 사용하였습니다.**

**세번째로 S3저장소입니다. 어디서나 원하는 양의 데이터를 저장하고 검색할 수 있는 저장소입니다. **

**Ec2로 기본적인 서버를 만들고 rds를 생성, ec2서버와 연동하여 서버를 구성하고 데이터 베이스에 저장하기 힘든 동영상파일을 주고 받아야하기에**

**Aws에서 제공하는s3 저장소를 사용하여 동영상 데이터를 처리 하였습니다 ** 



## 서버 

![image-20210706103437054](https://user-images.githubusercontent.com/67422547/124547511-378e4300-de67-11eb-9984-5b56585f9b2c.png)

## DB Table

**Apply(신청서)**

![image-20210706110042224](https://user-images.githubusercontent.com/67422547/124547524-3d842400-de67-11eb-82b1-9587be154922.png)



**S3(동영상 저상소)**

![image-20210706110127773](https://user-images.githubusercontent.com/67422547/124547535-4117ab00-de67-11eb-9272-a963e8647f60.png)

동영상에 매핑되는 code와 S3저장소에 저장된 동영상의 위치URL



**User**

![image-20210706110318972](https://user-images.githubusercontent.com/67422547/124547551-483eb900-de67-11eb-9c99-c5f58f319b12.png)

**Cel(셀럽)**

![image-20210706110341232](https://user-images.githubusercontent.com/67422547/124547565-4c6ad680-de67-11eb-9e75-7c2504504bb7.png)

## API 기능

### S3/Apply/Cel/User 의 정보 CRUD



``` java
 @GetMapping("/S3")
    //모든 S3 보여주기
    public List<S3> retrieveAllS3(){
        return S3Repository.findAll();
    }

    @GetMapping("/S3/{code}")
    //code를 이용하여 동영상 위치URL불러오기
    public S3 retrieveS3code(@PathVariable String code){
        Optional<S3> S3 = S3Repository.findBycode(code);

        return S3.get();
    }

    @DeleteMapping("/S3/{id}")
    //삭제
    public void deleteS3(@PathVariable Integer id){
        S3Repository.deleteById(id);

    }

    @PostMapping("/S3")
    //추가
    public ResponseEntity<S3> createS3(@Valid @RequestBody S3 S3){
        S3 savedS3 = S3Repository.save(S3);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedS3.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
```

![image-20210706120026151](https://user-images.githubusercontent.com/67422547/124547585-52f94e00-de67-11eb-83b4-edd02cff9115.png)

**Post man을 이용하여 api를 통해 S3 테이블의 정보를 불러들임 **



**기타 API기능**

|                                                              | **방식** | **주소**                                             | **입력 값**                                                  | **결과 값**                                                  |
| ------------------------------------------------------------ | -------- | ---------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **로그인** **토큰받기**                                      | POST     | http://3.36.228.255:8080/api/authenticate            | BODY:  {  "username":"celebrity-IU",  "password":"celebrity"  } | {    "token": ""  }                                          |
| **토큰으로 유저정보 받기**                                   | GET      | http://3.36.228.255:8080/api/user                    | fetch(/api/user',  {   method: 'GET',   headers: {    'Authorization': 'Bearer' + authToken(발급받은 토큰을 저장한 지역변수)   }  })  .then(res  => res.json())  .then(data  => { console.log(data) })  .catch(err  => { console.log(err) }) | {    "username":  "celebrity-IU",    "nickname": "아이유",    "authorities": [      {        "authorityName":  "ROLE_CEL"      }    ]  } |
| **셀럽의**  **이름으로** **셀럽이**  **받은** apply들 가져오기 | GET      | http://3.36.228.255:8088/jpa/apply/Bycel/셀럽의 이름 |                                                              | {      "apply_id":  3,      "name": "minsuk",      "email":  "rrrrrr@naver.com\t",      "title": "title",      "request": "요청사항2",      "story": "사연입니다. 사연2",      "selectedYoutuberName":  "IU",      "code": "awd",      "userId":  null  } |
| **title**로 **apply**  **찾고  해당** **apply**의 동영상code수정 | PUT      | http://3.36.228.255:8088/jpa/apply/modify            | BODY:    {      "title": "찾아야할 apply의 제목",      "code": "수정할 동영상 코드"    } |                                                              |



**이 외에도 PANDA application에 필요한 다양한 api를 제공하고있습니다.**