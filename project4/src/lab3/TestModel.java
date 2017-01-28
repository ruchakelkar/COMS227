package lab3;

public class TestModel
{
  public static void main(String[] args)
  {
    RabbitModel2 model2 = new RabbitModel2();
    
    // Check that the initial population is 2
    System.out.println(model2.getPopulation());
    System.out.println("Expected 0");
    
    // A year goes by...
    model2.simulateYear();
    model2.simulateYear();
    model2.simulateYear();
    model2.simulateYear();
    model2.simulateYear();
    model2.simulateYear();
    model2.simulateYear();
    model2.simulateYear();
    model2.simulateYear();
    model2.simulateYear();
    model2.simulateYear();
    System.out.println(model2.getPopulation());
    System.out.println("Expected 1");
    
    // Start over
    model2.reset();
    System.out.println(model2.getPopulation());
    System.out.println("Expected 0");
  }
}