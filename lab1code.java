import java.util.*;
import java.lang.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;

class Reader {
	static BufferedReader reader;
	static StringTokenizer tokenizer;

	/** call this method to initialize reader for InputStream */
	static void init(InputStream input) {
		reader = new BufferedReader(
				new InputStreamReader(input) );
		tokenizer = new StringTokenizer("");
	}

	/** get next word */
	static String next() throws IOException {
		while ( ! tokenizer.hasMoreTokens() ) {
			//TODO add check for eof if necessary
			tokenizer = new StringTokenizer(
					reader.readLine() );
		}
		return tokenizer.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt( next() );
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble( next() );
	}
}

class Case
{
	private lawyer Lawyer;
	private client Client;
	public Case(lawyer Lawyer,client Client)
	{
		this.Lawyer = Lawyer;
		this.Client = Client;
	}
	public lawyer getLawyer() {
		return Lawyer;
	}
	public void setLawyer(lawyer lawyer) {
		Lawyer = lawyer;
	}
	public client getClient() {
		return Client;
	}
	public void setClient(client client) {
		Client = client;
	}
}

class lawyer
{
	private ArrayList <client> pending_cases = new ArrayList<client>();
	private client current_case;
	private int category_index;
	private String name;
	private final int id;
	private String specialization;

	public lawyer(int id)
	{
		this.id = id;
		this.current_case=null;
	}


	public int getId() {
		return id;
	}


	public int getCategory_index() {
		return category_index;
	}


	public void setCategory_index(int category_index) {
		this.category_index = category_index;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public int getPending_cases_no() {
		return pending_cases.size();
	}


	public ArrayList<client> getPending_cases() {
		return pending_cases;
	}


	public void setPending_cases(ArrayList<client> pending_cases) {
		this.pending_cases = pending_cases;
	}

	public void remove_client(int pos)
	{
		client goal = this.pending_cases.get(pos);
		if(goal!=null)
		{
			this.current_case = goal;
			pending_cases.remove(pos);
		}
	}



	public client find_next_client()
	{
		client goal = null;
		int min_lvl = 6;
		int min_id = Integer.MAX_VALUE;
		int pos = -1;//pos where goal found
		for(int i=0;i<this.getPending_cases_no();i++)
		{
			client cur = this.pending_cases.get(i);

			if(cur.getPrior_lvl()<min_lvl || ( cur.getPrior_lvl()==min_lvl && cur.getId()<min_id) )
			{
				//first come, first serve
				min_lvl = cur.getPrior_lvl();
				min_id =  cur.getId();
				goal = cur;
				pos = i;
			}

		}
		//delete from pending cases
		if(pos!=-1)
			{
			this.remove_client(pos);
			}
		return this.current_case;
	}


	public client getCurrent_case() {
		return current_case;
	}


	public void setCurrent_case(client current_case) {
		this.current_case = current_case;
	}

	public void show_pending_cases()
	{
		if(this.getPending_cases_no()==0)
		{
			System.out.println("No pending cases");
		}
		else
		{
			for(int i=0;i<this.getPending_cases_no();i++)
			{
				client cur = this.pending_cases.get(i);	
				cur.display();
			}			
		}
		return;
	}

	public void remove_client_demand(int id)
	{
		for(int i=0;i<this.getPending_cases_no();i++)
		{
			client cur = this.pending_cases.get(i);	
			if(cur.getId()==id)
			{
//				System.out.println("Found");
				this.pending_cases.remove(i);
			}
		}
		return;
	}



	public void display() {
		System.out.println(this.name+" "+this.specialization+" "+this.getPending_cases_no());
		
	}





}

class client
{
	private int category_index;
	private String name;
	private String category; // category of legal dispute
	private lawyer client_lawyer;
	private int id;
	private final int prior_lvl;


	public int getCategory_index() {
		return category_index;
	}

	public int getPrior_lvl() {
		return prior_lvl;
	}

	public void setCategory_index(int category_index) {
		this.category_index = category_index;
	}

	public client(int id,int prior_lvl) {
		this.id = id;
		this.prior_lvl= prior_lvl;
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public lawyer getClient_lawyer() {
		return client_lawyer;
	}

	public void setClient_lawyer(lawyer client_lawyer) {
		this.client_lawyer = client_lawyer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void display() {
		client goal =this;
		System.out.println(goal.getName()+" "+goal.getCategory()+" "+goal.getId() );

	}



}

class firm
{
	private final String a[];
	private static ArrayList<ArrayList<lawyer>> list_lawyer = new ArrayList<ArrayList<lawyer>>(4);
	private static ArrayList<client> list_client = new ArrayList<client>();
	//	ArrayList <lawyer> list_family_law = new ArrayList <> ();
	//	ArrayList <lawyer> list_defamation = new ArrayList <> ();
	//	ArrayList <lawyer> list_housing = new ArrayList <> ();
	//	ArrayList <lawyer> finance = new ArrayList <> ();
	private static int lawyer_id = 10;
	private static int client_id = 20;


	public firm() {
		this.a = new String[4];
		a[0] = "family";
		a[1] = "defamation";
		a[2] = "housing";
		a[3] = "finance";
		for(int i=0;i<4;i++)
		  {
			ArrayList<lawyer> e = new ArrayList<lawyer>();
		  	list_lawyer.add(e);
		  }
	}

	public static int getLawyer_id() {
		return lawyer_id;
	}

	public static void setLawyer_id(int lawyer_id) {
		firm.lawyer_id = lawyer_id;
	}

	public static int getClient_id() {
		return client_id;
	}

	public static void setClient_id(int client_id) {
		firm.client_id = client_id;
	}

	public static lawyer find_lawyer(int category_index) {
		lawyer suit = find_min_lawyer(category_index);
		return suit;
	}

	public static lawyer find_min_lawyer(int category_index)
	{
		int min = list_lawyer.get(category_index).get(0).getPending_cases_no();
		lawyer best = list_lawyer.get(category_index).get(0);
		for(int i=0;i< list_lawyer.get(category_index).size() ;i++)
		{
			int cur = list_lawyer.get(category_index).get(0).getPending_cases_no();
			if(cur<min)
			{
				min = cur;
				best = list_lawyer.get(category_index).get(i);
			}
		}
		return best;
	}


	public void new_lawyer(String name,String specialization)
	{
		/* All lawyers will be registered before any other query is made. Inputs: lawyer name,
specialization. Each lawyer should be assigned an unique id.*/ 
		int f=0;
		int index=-1;
		for(int i=0;i<4;i++)
		{
			
			if(specialization.equals(a[i]))
			{
				f=1;
				index = i;
				break;
			}
		}
		if(f==1)
		{
			setLawyer_id(lawyer_id+1);
			lawyer l = new lawyer(getLawyer_id());
			l.setName(name);
			l.setSpecialization(specialization);
			l.setCategory_index(index);
			System.out.println(l.getId());
			switch(l.getSpecialization())
			{
			case "family": list_lawyer.get(0).add(l);
			break;
			case "defamation": this.list_lawyer.get(1).add(l);
			break;
			case "housing": this.list_lawyer.get(2).add(l);
			break;
			case "finance": this.list_lawyer.get(3).add(l);
			break;
			}
		}
		else
		{
			System.out.println("Invalid specialization");
		}
		return;
	}

	public void new_client(String name,String category, int prior_lvl)
	{
		/* This query will involve assigning an unique id, a priority level and a lawyer to the
client. Inputs: client name, category of legal dispute, and priority level. All client
names will be single words. Display the unique id, and name of lawyer assigned.
		 */ 	
		int f=0;
		int index=-1;
		for(int i=0;i<4;i++)
		{
			if(category.equalsIgnoreCase(a[i]) )
			{
				f=1;
				index = i;
				break;
			}
		}		
		if(f==1)
		{
			setClient_id(client_id+1);
			client c = new client(getClient_id(),prior_lvl);
			c.setCategory(category);
			c.setName(name);
			c.setCategory_index(index);
			lawyer found = find_min_lawyer(c.getCategory_index());
			c.setClient_lawyer(found);
			System.out.println(c.getId()+" "+c.getClient_lawyer().getName());
			lawyer l =c.getClient_lawyer();
			l.getPending_cases().add(c);
			list_client.add(c);

		}

		else
		{
			System.out.println("Invalid category of legal dispute");

		}



		return;
	}

	public static lawyer find_lawyer_by_id(int id)
	{
		lawyer found = null;
		for(int i=0;i<list_lawyer.size();i++)
		{
			ArrayList<lawyer> now = list_lawyer.get(i);
			for(int j=0;j<now.size();j++)
			{
				if(now.get(j).getId()==id)
				{
					found = now.get(j);
					break;

				}
			}
		}
		return found;
	}

	public void show_next_case(int lawyer_id)
	{
		/* Each client will be assigned a priority level beforehand by the people at the
		front desk. This priority can be determined from a lot of factors like date of
		hearing, type of client, etc. You, as the system designer, do not need to worry
		about these factors and simply accept a user-provided priority level. There are
		five levels of client priority in this firm (1-5, in decreasing order of priority).
		b. Clients belonging to the same priority level will be processed on
		first-come-first-serve basis​. (Assume that a lawyer in this firm can only work
		on one case at a time.)4. After a client has been assigned a priority level, no one else should be able to
		change the priority level. It must remain fixed till the end.	*/ 


		/* This case should be decided on the basis of the priority rules mentioned earlier. This
operation should remove this case from the list of pending cases for that lawyer.
Input: lawyer id. Display the name of the client, category of legal dispute and his/her
unique id	*/ 

		lawyer cur = find_lawyer_by_id(lawyer_id);
		if (cur==null)
		{
			System.out.println("Invalid lawyer id");
		}
		else
		{
			client goal = cur.find_next_client();
			if(goal==null)
			{
				System.out.println("No pending cases");
			}
			else
			{
				System.out.println(goal.getName()+" "+goal.getCategory()+" "+goal.getId() );
			}
		}
	}

	public void show_client_det(int lawyer_id)
	{/* Show details of the current client being handled by a given lawyer
Input: lawyer id. Output the same details of the client as query 2.	*/ 

		lawyer cur = find_lawyer_by_id(lawyer_id);
		if(cur!=null)
		{
		client cur_client = cur.getCurrent_case();
		if(cur_client==null)
		{
			System.out.println("No current case");			
		}
		else
		{
			cur_client.display();

		}
		}
		else
		{
			System.out.println("Invalid lawyer id");
		}
		return;
	}

	public static void pending_cases(int lawyer_id)
	{
		/* Show all pending cases of a given lawyer
Input: lawyer id. Output the same details of the client as query 2 for each pending
case/client.	*/ 
		lawyer cur = find_lawyer_by_id(lawyer_id);
		if(cur!=null)
		cur.show_pending_cases();
		else
		System.out.println("Invalid lawyer id");	
		return;
	}

	public static void all_lawyers()
	{
		/* 5. Show details of all lawyers in the firm
Display the name, specialization and number of pending cases of each lawyer	*/
		for(int i=0;i<4;i++)
		{		
		for(int j=0;j< list_lawyer.get(i).size() ;j++)
		{
			lawyer cur = list_lawyer.get(i).get(j);
			cur.display();
		}
		}
		return;
	}

	public static void remove_client(int client_id)
	{
		/* 6. Remove a client
Input: client id.	*/ 
		  int f=0;
		for(int i=0;i<list_client.size();i++)
		  {

		  	client cur = list_client.get(i);
		  	if(cur.getId()==client_id)
		  	{
		  		f=1;
		  		/* trying */
		  		lawyer law1 = cur.getClient_lawyer();
		  		int id = cur.getId();
		  		list_client.remove(i);
		  		law1.remove_client_demand(id);
		  	}
		  }
		if(f==0)
		{
			System.out.println("Invalid client id");
		}
		
		return;
	}
}

public class lab1code{

	public static void main(String[] args) throws IOException
	{
		Reader.init(System.in);
		firm law = new firm();
		int n;
		n = Reader.nextInt();
		for(int i=0;i<n;i++)
		{
			String lawyer_name,spec;
			lawyer_name = Reader.next();
			spec = Reader.next();	
			law.new_lawyer(lawyer_name, spec);
		}

		int q;
		q = Reader.nextInt();
		for(int i=0;i<q;i++)
		{
			int query;
			query = Reader.nextInt();
			switch (query) 
			{
			case 1: String name ,category; 
			int prior_lvl;
			name = Reader.next();
			category = Reader.next();
			prior_lvl = Reader.nextInt();
			law.new_client(name, category, prior_lvl);
			break;
			case 2: int lawyer_id;
			lawyer_id = Reader.nextInt();
			law.show_next_case(lawyer_id);
			break;
			case 3: int lawyern_id;
			lawyern_id = Reader.nextInt();
			law.show_client_det(lawyern_id);
			break;
			case 4: int law_id;
			law_id = Reader.nextInt();
			law.pending_cases(law_id);
			break;
			case 5: law.all_lawyers();
			break;
			case 6: int client_id = Reader.nextInt();
					law.remove_client(client_id);
					break;
			default: System.out.println("Invalid query");
				break;

			}

		}
	}
}