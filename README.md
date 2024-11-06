
### maven
* mvn package(create thr jar in the target which can be shared)
* mvn clean(clean the taget)
* To run the project -> cd taget -> java -jar journalApp-0.0.1-SNAPSHOT.jar 

### Ioc Container and Dependency Injection

#### break
* @Component
  public class Break {

  public String applyBreak(){
  return "stop";
  }
  }
#### car
* @RestController
  public class Car {
    @Autowired
    private Break aBreak;

    @GetMapping("ok")
    public String ok(){
        return aBreak.applyBreak();
    }
  }

### Restful Api