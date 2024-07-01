import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class MonthWise extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();



Double amount=Double.parseDouble(req.getParameter("amount"));
        double annualInterestRate = Double.parseDouble(req.getParameter("interest"));
Double months=Double.parseDouble(req.getParameter("months"));

        double monthsInterestRate = annualInterestRate / 12 / 100;

        double emi = (amount * monthsInterestRate * Math.pow(1 + monthsInterestRate, months)) / (Math.pow(1 + monthsInterestRate, months) - 1);

        out.println("<html><body>");
out.println("<div style=\"text-align: center; background-color: gray; margin-left:160px;margin-right:160px;\">");
out.println("<br><br>Principal Amount = " + amount);
out.println("<br><br>Rate of Interest per Year= "+annualInterestRate);
out.println("<br><br>Number of Months = "+months);
out.println("<br><br></div><br>");
        out.println("<table border=2 cellspacing=10 cellpadding=8 bgcolor=yellow align=center width=1000px> ");

out.println("<tr>");
out.println("<th colspan=2 bgcolor=lightgray>Every Month</th> ");
out.println("<th bgcolor=lightgray>Amount Paid(Principal+interest)</th> ");
//out.println("<th>Extra Amount Paid</th> ");
out.println("</tr>");
    
	
	double actualAmount=amount;
  double interests=0.0;
  double totalInterest=0.0;
  double totalMonthsAmunt=0.0;
        // months wise 
        for (int i = 1; i <= months; i++) {
              interests = calcMonthInterest(amount, monthsInterestRate);
			  totalInterest+=interests;
            double principalAmount = emi - interests;
            amount = amount - principalAmount;
                    out.println("<tr>");

            out.println("<td>Month: (" + i + ") Principal Amount = "+principalAmount+" </td>");
			out.println("<td>Interest: "+interests+"</td>");
			out.println("<td>Month :("+i+") TotalPaid "+(principalAmount+interests)+"</td>");
				        out.println("</tr>");



        }
	out.println("<tr colspan=3 ><th colspan=2 bgcolor=red>TotalPaid </th><td bgcolor=pink> "+(actualAmount+totalInterest)+"</td></tr>");
	      out.println("<tr colspan=3><th colspan=2 bgcolor=red>TotalExtraPaid </th><td bgcolor=pink> "+totalInterest+"</td></tr>");

        out.println("</table>");
        out.println("</body></html>");
    }

    private double calcMonthInterest(double leftAmount, double monthsInterestRate) {
        return leftAmount * monthsInterestRate;
    }
}
