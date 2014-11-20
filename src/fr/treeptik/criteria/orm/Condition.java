package fr.treeptik.criteria.orm;

public class Condition {

	
	
	
	
	private String propertyName;
	private Object value;
	private String operator;
	
	
	public static Condition eq(String propertyName, Object value){
		Condition c = new Condition();
		c.setPropertyName(propertyName);
		c.setValue(value);
		c.setOperator("=");
		
		return c;
	}
	
	
	public static Condition inf(String propertyName, Object value){
		Condition c = new Condition();
		c.setPropertyName(propertyName);
		c.setValue(value);
		c.setOperator("<");
		
		return c;
	}
	
	public static Condition supp(String propertyName, Object value){
		Condition c = new Condition();
		c.setPropertyName(propertyName);
		c.setValue(value);
		c.setOperator(">");
		
		return c;
	}
	
	
	public static Condition like(String propertyName, Object value){
		Condition c = new Condition();
		c.setPropertyName(propertyName);
		c.setValue(value);
		c.setOperator("like");
		
		return c;
	}
	//BETWEEN 'valeur1' AND 'valeur2'
	public static Condition between(String propertyName, Object value1, Object value2){
		Condition c = new Condition();
		c.setPropertyName(propertyName);
		c.setValue(value1);
		c.setValue(value2);
		c.setOperator("BETWEEN ? AND");
		
		return c;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	
	
	
}
