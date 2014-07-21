/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.26
 * Generated at: 2014-07-21 07:35:01 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import model.*;

public final class immobilePage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\t\t <meta charset=\"UTF-8\"> <!-- accetta determinati caratteri ad es. -->\n");
      out.write("\n");
      out.write("\t\t<title>Agenzia Immobiliare</title>\n");
      out.write("\t\t<link type=\"text/css\" rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("\t\t<link rel=\"shortcut icon\" href=\"images/favicon.ico\" /> \n");
      out.write("\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("\t\n");
      out.write("   \t<div id=\"puntina1\"> <img src= \"images/tack.png\" width=\"100\" height=\"100\"></div>\n");
      out.write("\t<div id=\"puntina2\"> <img src= \"images/tack.png\" width=\"100\" height=\"100\"></div>\n");
      out.write("\t<div id=\"puntina3\"> <img src= \"images/tack.png\" width=\"100\" height=\"100\"></div>\n");
      out.write("\t\n");
      out.write("\t<div class=\"header\">\n");
      out.write("\t\n");
      out.write("\t\t<div id=\"menu\"> \n");
      out.write("\n");
      out.write("\t\t<div class=\"logo\"><h1><a href=\"./main\">Agenzia Immobiliare</a> </h1> </div>\n");
      out.write("\n");
      out.write("\t\t\t<ul>\n");
      out.write("\t\t\t   <li><a href=\"./main\">Home</a></li>\n");
      out.write("\t\t\t   <li><a href=\"./main#chisiamo\">Chi siamo</a></li>\n");
      out.write("\t\t\t   <li><a href=\"./main#invendita\">Cerco casa</a></li>\n");
      out.write("\t\t\t   <li class=\"no_bg ped\"><a href=\"./main#contactus\">Dove siamo</a></li>\n");
      out.write("\t\t\t</ul>\n");
      out.write("\t\t</div> <!-- end menu-->\t\n");
      out.write("\t\t\n");
      out.write("\t</div> <!-- end header-->\n");
      out.write("\t\n");
      out.write("\t<div class=\"content\">\n");
      out.write("\t\t<div class=\"container\">\n");
      out.write("\n");
      out.write("\n");
      out.write("     ");
 
        Vector infoImmobile = (Vector)request.getAttribute("infoImmobile"); 
        agenziaImmoBean bean = (agenziaImmoBean) infoImmobile.get(0);
        String link = "./main?ps=offerte_"; link+= bean.getCodiceImm(); 
        int numOff= (Integer)request.getAttribute("numOfferte");
      
      out.write("\n");
      out.write("\n");
      out.write("\t\t<div class=\"titolo\"> <h2>Pagina relativa alle informazioni dell'immobile.</h2></div>\n");
      out.write("\t\t\t<h3> <ul>\n");
      out.write("                <li> Codice: ");
      out.print( bean.getCodiceImm() );
      out.write("</li> \n");
      out.write("\t\t\t\t<li> Indirizzo: ");
      out.print( bean.getIndirizzoImm() );
      out.write("</li> \n");
      out.write("\t\t\t    <li> Citt&agrave: ");
      out.print( bean.getCittaImm() );
      out.write("</li> \n");
      out.write("\t\t\t\t<li> Superficie totale: ");
      out.print( bean.getSupTotImm() );
      out.write("</li> \n");
      out.write("\t\t\t\t<li> Numero vani: ");
      out.print( bean.getNumVani() );
      out.write("</li> \n");
      out.write("                <li> Descrizione: ");
      out.print( bean.getDesc() );
      out.write("</li>\n");
      out.write("                           <li> Prezzo minimo: ");
      out.print( bean.getPrezzoMinimo() );
      out.write(" </li>\n");
      out.write("                \n");
      out.write("               \n");
      out.write("                <li> Numero offerte di acquisto pervenute: ");
      out.print( numOff );
      out.write(" </li>\n");
      out.write("\n");
      out.write("                FOTO!!! <BR>\n");
      out.write("\n");
      out.write("\t\t\t</h3>\n");
      out.write("\t\t</div> <!-- container end -->\n");
      out.write("\n");
      out.write("\n");
      out.write("    <div class=\"container\">\n");
      out.write("\n");
      out.write("    <div id=\"two-columns\">\n");
      out.write("\t\t\t    \n");
      out.write(" <!--***** FORM PER IL LOGIN   ****-->\n");
      out.write("    <div id=\"col1\">\n");
      out.write("\t      <div class=\"login-card\">\n");
      out.write("\n");
      out.write("\t\t        <h1>Log-in</h1><br>\n");
      out.write("                \n");
      out.write("\t\t      <form name=\"login\" action=\"./main\" method=\"post\" > \n");
      out.write("                <input type = \"hidden\" name = \"codImm\" value = \"");
      out.print( bean.getCodiceImm() );
      out.write("\" >\n");
      out.write("                <input type = \"hidden\" name = \"ps\" value = \"login\" >\n");
      out.write("\t\t        <input type=\"text\" name=\"user\" value=\"Username\">\n");
      out.write("                <center><h4> password: </h4></center>\n");
      out.write("\t\t        <input type=\"password\" name=\"pass\">\n");
      out.write("\t\t        <input type=\"submit\" name=\"Entra\" value=\"login\">\n");
      out.write("\t\t      </form>\n");
      out.write("\t    </div>\t\n");
      out.write("            </div> <!--fine col1-->\n");
      out.write("    \n");
      out.write("\n");
      out.write("\n");
      out.write(" <!--*****\t     <div class=\"login-card\">\n");
      out.write("\t\t        <h1>Benvenuto Taccarlo!</h1><br>\n");
      out.write("\t\t     \n");
      out.write("\n");
      out.write("\t\t      <div class=\"login-help\" align=\"center\" >\n");
      out.write("\t\t        <a href=\"#\">Vai alle offerte</a> ------- <a href=\"#\">Change user</a>\n");
      out.write("\t\t      </div>\n");
      out.write("\t    </div>\t\n");
      out.write("\n");
      out.write(" FORM PER L'OFFERTA *******-->\n");
      out.write("\n");
      out.write("           <div id=\"col2\">\n");
      out.write("\t      <div class=\"login-card\">\n");
      out.write("\t\t        <h1>Inserisci Offerta</h1><br>\n");
      out.write("\n");
      out.write("\t\t      <form name=\"faiOfferta\" action=\"./main\" method=\"post\">\n");
      out.write("\n");
      out.write("                <input type = \"hidden\" name = \"idTent\" value = \"100\" >\n");
      out.write("                <input type = \"hidden\" name = \"ps\" value = \"inserimento\" >\n");
      out.write("                <input type = \"hidden\" name = \"codiceImm\" value = ");
      out.print( bean.getCodiceImm() );
      out.write(" >    \n");
      out.write("                   \n");
      out.write("\t\t        <input type=\"text\" name=\"Nome\" value=\"Inserisci nome\">\n");
      out.write("                   \n");
      out.write("\t\t        <input type=\"text\" name=\"Cognome\" value=\"Inserisci cognome\">\n");
      out.write("                  \n");
      out.write("\t\t        <input type=\"text\" name=\"Telefono\" value=\"Inserisci telefono\">\n");
      out.write("                  \n");
      out.write("\t\t        <input type=\"text\" name=\"CifraOfferta\" value=\"Inserisci cifra offerta\">\n");
      out.write("\n");
      out.write("\t\t        <input type=\"submit\" name=\"Invia\" value=\"login\">\n");
      out.write("\t\t      </form>\n");
      out.write("\n");
      out.write("\t    </div>\t\n");
      out.write("        </div> <!--**fine col2 -->\n");
      out.write("        </div> <!--**fine 2 colonne -->    \n");
      out.write("\n");
      out.write("\t    </div>\t<!-- content-end-->\n");
      out.write("</div> <!-- fine container  -->\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("<footer>\n");
      out.write("\t<h3> Progetto prodotto da Pietro Musoni e Carlo Tacchella. Corso di Base di dati. Anno 2013/2014.</h3>\n");
      out.write("</footer>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
