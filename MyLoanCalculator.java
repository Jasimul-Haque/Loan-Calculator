package loanTag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyLoanCalculator extends SimpleTagSupport {
private double annualInterestRate=0.0;
private int numberOfYears=0;
private double loanAmount=0.0;
private java.util.Date loandate;

public void setannualInterestRate(double rate){
	this.annualInterestRate=rate;
}
public void setloanAmount(double amount){
	this.loanAmount=amount;
}
public void setnumberOfYears(int years){
	this.numberOfYears=years;
}
StringWriter writer= new StringWriter();
public void doTag() throws JspException, IOException {
double monthlyInterestRate= annualInterestRate/1200;
double monthlyPayment=loanAmount*monthlyInterestRate/(1-(1/Math.pow(1+monthlyInterestRate, numberOfYears *12)));
if(annualInterestRate>0 && numberOfYears >= 1 && loanAmount >0){
	JspWriter out=getJspContext().getOut();
	out.println(String.format("$%.2f", monthlyPayment)+"<br>");
	
}else{
	getJspBody().invoke(writer);
	getJspContext().getOut().println(writer.toString());
}

}
}

