package org.jboss.webbeans.test.unit.xml;

import java.lang.reflect.AnnotatedElement;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.jboss.webbeans.test.unit.AbstractWebBeansTest;
import org.jboss.webbeans.test.unit.xml.beans.Order;
import org.jboss.webbeans.util.xml.XmlParserImpl;
import org.testng.annotations.Test;


public class XmlParserImplTest extends AbstractWebBeansTest
{
   @Test
   public void testParse()
   {
      Set<URL> xmls = new HashSet<URL>();
      Iterable<URL> urls = getResources("user-defined-beans.xml");

      for (URL url : urls)
         xmls.add(url);

      XmlParserImpl parser = new XmlParserImpl();
      Set<AnnotatedElement> aSet = parser.parse(xmls);

      for(AnnotatedElement aElement : aSet){
    	  assert aElement.equals(Order.class);
      }
      assert aSet.size() == 1;
   }
}

/*
<Beans xmlns="urn:java:ee" xmlns:myapp="urn:java:com.mydomain.myapp"
xmlns:test="urn:java:org.jboss.webbeans.test.unit.xml">
<Deploy>
	<Standard />
	<Production />
	<test:AnotherDeploymentType />
</Deploy>
<myapp:Order>
	<ConversationScoped />
	<myapp:PaymentProcessor>
		<myapp:Asynchronous />
	</myapp:PaymentProcessor>
	<myapp:User />
</myapp:Order>
<myapp:Login>
	<ConversationScoped />
	<BindingType />
</myapp:Login>
</Beans>
*/
