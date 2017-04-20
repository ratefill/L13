package by.epam.l13.homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/*Проанализируйте библиотеку IO Streams. Напишите собственные
примеры сериализации, десериализации.
*/
public class HW01serialize
{
	public static void main(String[] args)
	{
		
		Model m = new Model();
		m.print();
		m.save();
		m.setRecord(2, "second");
		m.print();
		m.load();
		m.print();
	}

}

class Model
{
	private String fileName = "d:\\Documents\\JAVA\\src\\by\\epam\\l13\\homework\\db.dat";
	private Record r;


	public Model()
	{
		super();
		r = new Record(1, "first");
	}

	public void save()
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(r);

			oos.close();
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void load()
	{
		final File f = new File(fileName);

		try
		{
			if (f.exists())
			{

				FileInputStream fos = new FileInputStream(fileName);
				ObjectInputStream oos = new ObjectInputStream(fos);

				r = (Record) oos.readObject();

				oos.close();
			}

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setRecord(int id, String name)
	{
		r = new Record(id, name);
	}

	public void print()
	{
		System.out.println("id=" + r.id + ", name=" + r.name+ ", gen="+r.gen);
	}

}


class Record implements Serializable
{
	private static final long serialVersionUID = 8873253109760144156L;
	public transient int gen;
	public int id;
	public String name;

	Record(int id, String name)
	{
		this.id = id;
		this.name = name;
		Random rand = new Random();
		this.gen=rand.nextInt(100);
	}
}	


