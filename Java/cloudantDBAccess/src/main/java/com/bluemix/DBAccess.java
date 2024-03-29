package com.bluemix;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.Resource;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;

/**
 * Servlet implementation class DBAccess
 */
@WebServlet("/DBAccess")
public class DBAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBAccess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @SuppressWarnings("restriction")
	@Resource(name = "couchdb/HackPrep-cloudantNoSQLDB")
    protected CouchDbInstance _db;
     
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dbname = "my_database";
        try {
            //creates a database with the specified name
            CouchDbConnector dbc = _db.createConnector(dbname, true);
            response.getWriter().println("Created database: " + dbname);
            //create a simple doc to place into your new database
            Map<String, Object> doc = new  HashMap<String, Object>();
            doc.put("_id",  UUID.randomUUID().toString());
            doc.put("season", "summer");
            doc.put("climate", "arid");
            dbc.create(doc);
            response.getWriter().println("Added a simple doc!");
        } catch (Exception e) {
            response.getWriter().println(e.getMessage());
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
