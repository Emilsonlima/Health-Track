package br.com.Healthtrack.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class imc
 */
@WebServlet("/imc")
public class imc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public float imc;
    public String condicao;
        
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            IOException, ServletException{
        
       
        String vl_altura = request.getParameter("vl_altura");
        String vl_peso = request.getParameter("vl_peso");
        
        imc = Float.parseFloat(vl_peso) / (Float.parseFloat(vl_altura) * Float.parseFloat(vl_altura));
        
        
        
        if(imc < 18)
        {
            condicao = "Abaixo do peso";
        }
        else
            if(imc > 18 & imc < 25)
            {
                condicao = "Peso normal";
            }
            else
                if(imc > 25 & imc < 30)
                {
                    condicao = "Acima do peso";
                }
                else
                    if(imc > 30)
                    {
                        condicao = "obesso";
                    }
        
        request.setAttribute("condicao", condicao);
        
        RequestDispatcher rd = request.getRequestDispatcher("ResultadoIMC.jsp");
        rd.forward(request, response);
        
        
    }
}