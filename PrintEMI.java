import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class PrintEMI extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        double amount = (Double) req.getAttribute("amount");
        double annualInterestRate = (Double) req.getAttribute("interest");
        double months = (Double) req.getAttribute("months");

req.setAttribute("amount",amount);
req.setAttribute("interest",annualInterestRate);
req.setAttribute("months",months);

        double monthsInterestRate = annualInterestRate / 12 / 100;

        double emi = (amount * monthsInterestRate * Math.pow(1 + monthsInterestRate, months)) / (Math.pow(1 + monthsInterestRate, months) - 1);

        out.println("<html><body>");
out.println("<div style=\"text-align: center; background-color: gray; margin-left:200px;margin-right:200px;\">");
out.println("<br><br>Principal Amount = " + amount);
out.println("<br><br>Rate of Interest per Year= "+annualInterestRate);
out.println("<br><br>Number of Months = "+months);
out.println("<br><br></div><br>");
        out.println("<table border=2 cellspacing=10 cellpadding=8 bgcolor=yellow align=center width=900px>");

out.println("<tr>");
out.println("<th colspan=2>Every Month</th> ");
out.println("<th>Total Amount Paid</th> ");
out.println("<th>Extra Amount Paid</th> ");
out.println("</tr>");
    
	
	
  double interests=0.0;
  double actualAmount=amount;
  double totalInterest=0.0;
  double totalMonthsAmunt=0.0;
        // months wise 
        for (int i = 1; i <= months; i++) {
              interests = calcMonthInterest(amount, monthsInterestRate);
			   totalInterest+=interests;
            double principalAmount = emi - interests;
            amount = amount - principalAmount;
				totalMonthsAmunt=principalAmount+interests;
                   // out.println("<tr>");

            //out.println("<td>Month:" + i + " Amount </td><td> " + totalMonthsAmunt + "</td>");

        }
		out.println("<tr><td colspan=2 bgcolor=pink>"+totalMonthsAmunt+"</td>");
	out.println("<td bgcolor=pink>"+(actualAmount+totalInterest)+"</td>");
	out.println("<td bgcolor=pink>"+totalInterest+"</td>");
	        out.println("</tr>");

        out.println("</table>");

out.println("</body></html>");
    }

    private double calcMonthInterest(double leftAmount, double monthsInterestRate) {
        return leftAmount * monthsInterestRate;
    }
	
	
}
