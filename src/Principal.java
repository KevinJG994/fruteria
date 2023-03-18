import com.data.DataConnection;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.domain.Cliente;
import com.domain.Persona;
import com.domain.Producto;

public class Principal {
	
	private static ObjectContainer db= DataConnection.getInstance();
	
	public static void main(String[] args) {
			
		Persona p = new Persona("admin", "admin", true);
		Persona p1 = new Persona("user", "user", false);
			
		db.store(p);
		db.store(p1);
		
		Cliente c1 = new Cliente("44556611H","Juan", "Diaz", "123456789");
		Cliente c2 = new Cliente("44667711J", "Carlos", "Rodríguez", "987654321");
		Cliente c3 = new Cliente("99884411O", "Laura", "Fernández", "159357852");
		Cliente c4 = new Cliente("63056611P", "Marta", "Romero", "357789321");
		Cliente c5 = new Cliente("66554477T", "Lucía", "Martínez", "123654852");
		
		db.store(c1);
		db.store(c2);
		db.store(c3);
		db.store(c4);
		db.store(c5);
		
		Producto pro = new Producto("1", "Batata", "Lanzarote", 40);
		Producto pro1 = new Producto("2", "Berenjena", "Aragón", 75);
		Producto pro2 = new Producto("3", "Fresas", "Gran Canaria", 150);
		Producto pro3 = new Producto("4", "Manzana Royal Gala", "Girona", 53);
		Producto pro4 = new Producto("5", "Naranjas", "Valencia", 100);
		Producto pro5 = new Producto("6", "Papas Negras", "Tenerife", 110);
		Producto pro6 = new Producto("7", "Uva Roja", "Chile", 60);
		
		db.store(pro);
		db.store(pro1);
		db.store(pro2);
		db.store(pro3);
		db.store(pro4);
		db.store(pro5);
		db.store(pro6);
		
		db.close();
		
	}

}
