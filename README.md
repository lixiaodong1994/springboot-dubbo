###springboot-dubbo-zookeeper  
1.加入依赖  
  
	<dependencies>
	
		<dependency>
			<groupId>com.101tec</groupId>
			<artifactId>zkclient</artifactId>
			<version>0.9</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
2.提供者（pom文件中也需要加入zkclient依赖）  
(1)application.properties  
  
	spring.application.name=dubbo-spring-boot-starter
	spring.dubbo.server=true
	spring.dubbo.registry=zookeeper://localhost:2181
(2)写一个接口  
  
	public interface ServiceAPI {
	
	    String sendMessage(String msg);
	}
(3)写一个实现类
  
	@Service(interfaceClass = ServiceAPI.class)
	@Component
	public class QuickStartServiceImpl implements ServiceAPI {
	    @Override
	    public String sendMessage(String msg) {
	        return "quickStart-provider=" + msg;
	    }
	}
(4)springboot启动类  
  
	@SpringBootApplication
	@EnableDubboConfiguration
	public class ProviderApplication {
	
		public static void main(String[] args) {
			SpringApplication.run(ProviderApplication.class, args);
		}
	
	}
3.消费者(pom文件中也需要加入zkclient依赖)
(1)application.properties  
	
	spring.application.name=dubbo-spring-boot-starter
	spring.dubbo.registry=zookeeper://localhost:2181
(2)写一个和提供者同样的接口  
  
	public interface ServiceAPI {
	
	    String sendMessage(String msg);
	}
(3)消费类
  
	@Component
	public class QuickStartConsumer {
	    @Reference(interfaceClass = ServiceAPI.class)
	    ServiceAPI serviceAPI;
	
	    public void sendMsg(String message) {
	        System.out.println(serviceAPI.sendMessage(message));
	    }
	
	}  
(4)消费启动类
  
	@SpringBootApplication
	@EnableDubboConfiguration
	public class ConsumerApplication {
	
		public static void main(String[] args) {
			ConfigurableApplicationContext run = SpringApplication.run(ConsumerApplication.class, args);
			QuickStartConsumer quickStartConsumer = (QuickStartConsumer) run.getBean("quickStartConsumer");
			quickStartConsumer.sendMsg("哈哈哈哈哈");
		}
	
	}
