package lab6;
import balloon4.Balloon;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BalloonTests 
{   
    @Before
    public void setup()
    {
    	Balloon b = new Balloon(10);
    }
    
    @Test
    public void testInitialDiameter()
    {
    	String msg = "A newly constructed balloon should not be inflated.";
      	Balloon b = new Balloon(10);
    	assertEquals(0, b.getRadius());
    }
    
    @Test
    public void testInitial()
    {
    	String msg = "A newly constructed balloon should not be popped.";
      	Balloon b = new Balloon(10);
      	assertEquals(false, b.isPopped());
    }
    
    @Test
    public void testInflate()
    {
      	Balloon b = new Balloon(10);
      	b.blow(7);
      	assertEquals(7, b.getRadius());
    }
    
    @Test
    public void testMaxInflate()
    {
      	Balloon b = new Balloon(10);
      	b.blow(11);
      	assertEquals(true, b.isPopped());
    }
    
    @Test
    public void testDeflate()
    {
      	Balloon b = new Balloon(10);
      	b.deflate();
      	assertEquals(0, b.getRadius());
    }
    
    @Test
    public void testPop()
    {
      	Balloon b = new Balloon(10);
      	b.pop();
      	assertEquals(true, b.isPopped());
    }
    
    @Test
    public void testPopRadius()
    {
      	Balloon b = new Balloon(10);
      	b.pop();
      	assertEquals(0, b.getRadius());
    }
    
    @Test
    public void testDeflatePop()
    {
      	Balloon b = new Balloon(10);
      	b.deflate();
      	assertEquals(false, b.isPopped());
    }
    
    @Test
    public void testInflate2()
    {
      	Balloon b = new Balloon(10);
      	b.blow(2);
      	b.blow(5);
      	assertEquals(7, b.getRadius());
    }
    
    
    @Test
    public void testInflatePop()
    {
      	Balloon b = new Balloon(10);
      	b.blow(9);
      	b.pop();
      	b.blow(3);
      	assertEquals(0, b.getRadius());
    }
    
}
