package ap2;
import java.util.*;

import ap2.mainfile.Costumer;
import ap2.mainfile.Restaurant;
import ap2.mainfile.food;

public class menu {
	
	public static void main() {
		System.out.println("Welcome to Zotato");
		System.out.println("1) Enter as Restaurant Owner");
		System.out.println("2) Enter as Costumer");
		System.out.println("3) Check User Details");
		System.out.println("4) Company Account Details");
		System.out.println("5) Exit");
	}

	public static Restaurant mainfirst(Restaurant[] list) {
		Scanner s=new Scanner(System.in);
		System.out.println("Chose Restaurant");
		System.out.println("1) Shah (Authentic)");
		System.out.println("2) Ravi’s");
		System.out.println("3) The Chinese (Authentic)");
		System.out.println("4) Wang’s (Fast Food)");
		System.out.println("5) Paradise");
		int option=s.nextInt();
		return list[option-1];
	}
	
	//show info about a Costumer
	public static void cosinfo(Costumer current) {
		System.out.println(current.name+", pune, "+current.wallet);
	}
	
	//show info about a Restaurant
	public static void resinfo(Restaurant current) {
		System.out.println(current.resname+", "+current.cate);
	}
	
	
	//show all food of a restaurant
	public static food showfood(ArrayList<food> list) {
		Scanner s=new Scanner(System.in);
		System.out.println("Choose item by code");
		for(int i=0;i<list.size();i++) {
			food current=list.get(i);
			System.out.println(current.id+" "+current.name+" - "+current.name+" "+current.cost+" "+current.quantity+" "+current.offer+"% off "+current.category);
		}
		int chosed=s.nextInt();
		System.out.println("Enter item quantity");
		int q=s.nextInt();
		list.get(chosed-1).quantity-=q;
		return new food(list.get(chosed-1).name, list.get(chosed-1).category,list.get(chosed-1).cost,q, list.get(chosed-1).offer, list.get(chosed-1).rest);
	}
	
	public static void addfood() {
		System.out.println("1) Add item");
		System.out.println("2) Edit item");
		System.out.println("3) Print Rewards");
		System.out.println("4) Discount on bill value");
		System.out.println("5) Exit");
	}

	public static food fillfood(String rest, String restname,String restcate) {
		Scanner s=new Scanner(System.in);
		System.out.println("Enter food item details");
		System.out.println("Food name:");
		String foodname=s.nextLine();
		System.out.println("item price:");
		int price=s.nextInt();
		System.out.println("item quantity:");
		int quan=s.nextInt();
		System.out.println("item category:");
		String category=s.next();
		System.out.println("offer:");
		int offer=s.nextInt();
		food ff=new food(foodname, category,price ,quan, offer,rest);
//		ff.restaurant.resname = restname;
//		ff.restaurant.cate = restcate;
		System.out.println(ff.id+" "+foodname+" "+price+" "+quan+" "+offer+"% "+category);
		return ff;
	}
	
	public static Costumer personlist(Costumer[] colist) {
		Scanner s=new Scanner(System.in);
		System.out.println("1) Ram (Elite)");
		System.out.println("2) Sam (Elite)");
		System.out.println("3) Tim (Special)");
		System.out.println("4) Kim");
		System.out.println("5) Jim");
		int person=s.nextInt();
		return colist[person-1];
	}
	
	public static void resowner() {
		System.out.println("1) Add item");
		System.out.println("2) Edit item");
		System.out.println("3) print rewards");
		System.out.println("4) Discount on bill value");
		System.out.println("5) Exit");
		
	}
	
	public static void costumermenu() {
		System.out.println("Customer Menu");
		System.out.println("1) Select Restaurant");
		System.out.println("2) checkout cart");
		System.out.println("3) Reward won");
		System.out.println("4) print the recent orders");
		System.out.println("5) Exit");
	}
	
	public static void editfood(ArrayList<food> list,String resname) {
		Scanner s=new Scanner(System.in);
		System.out.println("Choose item by code");
//		System.out.println("size is "+list.size());
		for(int i=0;i<list.size();i++) {
			food current=list.get(i);
			System.out.println(current.id+" "+resname+" - "+current.name+" "+current.cost+" "+current.quantity+" "+current.offer+"% off "+current.category);
		}
		int changeinFI=s.nextInt();
		System.out.println("Choose an attribute to edit: ");
		System.out.println("1) Name");
		System.out.println("2) Price");
		System.out.println("3) Quantity");
		System.out.println("4) Category");
		System.out.println("5) Offer");
		int option=s.nextInt();
		if(option==1) {
			System.out.println("Enter new Name: ");		//changing name
			String newname=s.next();
			list.get(changeinFI-1).name=newname;
		}
		else if(option==2) {
			System.out.println("Enter new Price: ");		//changing price
			int newprice=s.nextInt();
			System.out.println(list.size()+" "+changeinFI);
			list.get(changeinFI-1).cost=newprice;
		}
		else if(option==3) {
			System.out.println("Enter new Quantity: ");			//chaning quantity
			int newq=s.nextInt();
			list.get(changeinFI-1).quantity=newq;
		}
		else if(option==4) {
			System.out.println("Enter new Category: ");			//changing category
			String newcat=s.next();
			list.get(changeinFI-1).category=newcat;
		}
		else {
			System.out.println("Enter new Offer: ");		//changing offer
			int newof=s.nextInt();
			list.remove(changeinFI);
			list.get(changeinFI-1).offer=newof;
		}
		
		System.out.println(list.get(changeinFI-1).id+" "+list.get(changeinFI-1).name+" "+list.get(changeinFI-1).cost+" "+list.get(changeinFI-1).quantity+" "+list.get(changeinFI-1).offer+"% "+list.get(changeinFI-1).category);
	}
}
