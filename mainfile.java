package ap2;
import java.util.*;
public class mainfile extends menu{
	public static int counter=1;
	
	public static float zotatoprofit=0;
	public static int deliveryprofit=0;
	
	public static  class Restaurant implements res {
		String resname;
		String cate;
		int reward=0;
		int discount=0;
		Costumer costumer;
		ArrayList<food> list=new ArrayList<>();
		Restaurant(String name,String cat){
			resname=name;
			cate=cat;
		}
		@Override
		public void AddFoodItem() {
			food n=fillfood(cate , resname, cate); //returns a food
			list.add(n);		//this food is added to ArrayList of food of the restaurant
//			n.restaurant.resname = resname;
//			n.restaurant.cate = cate;
		}
		@Override
		public void EditFoodItem() {
			editfood(list,resname);
		}
		@Override
		public void reward() {
			
			System.out.println("rewards "+reward);
		}
		@Override
		public void discount() {
			Scanner s=new Scanner(System.in);
			System.out.println("Enter offer on total bill value -");
			int offer=s.nextInt();
			discount=offer;
		}
		@Override
		public void exit() {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static class Costumer implements cos{
		String name;
		String cate;
		int reward;
		float wallet=1000;
		int cartno;
		Restaurant restaurant;
		ArrayList<food> cart=new ArrayList<>();
		Costumer(String name,String cate){
			this.cate=cate;
			this.name=name;
		}
		@Override
		public void select() {
			Restaurant rest=mainfirst(reslist);		//selected restaurant by the user
			restaurant = rest;		//costumer is linked with restaurant, now we can add reward points to restaurant as well
			food curr=showfood(rest.list);		//food and quantity which the user want to order
			cartno++;
			cart.add(curr);
			System.out.println("Items added to cart");
		}
		@Override
		public void checkout() {
			System.out.println("Item in Cart -");
			float bill=0;
			float fd=0;	//this is food discount. discount on indiviual food items
			for(int i=0;i<cart.size();i++) {
				foodinfo(cart.get(i));
				bill+=(cart.get(i).cost * cart.get(i).quantity);
				fd+=((float)cart.get(i).offer/100) * cart.get(i).cost * cart.get(i).quantity;			//food discount
			}
//			System.out.println("bill without any discount "+bill);
//			System.out.println("cart size is "+cart.size());
			//Authentic restaurants extra 50rupee off of bill greater than 100/-
			bill-=fd;	//restaurant discount
			
			float Restoffer = ((float)restaurant.discount / 100) * bill;		//restaurant discount
			bill -= Restoffer;
//			if(cart.get(0).rest=="Authentic" && bill>=100) {
//				bill-=50;
//			}
			
//			zotatoprofit+=100/bill;
			//bill-=reward;	//first reward is substracted
			//discount for Elite costumer
			int cd=0;	//costumer discount
			if(cate=="Elite" && bill>200) {
				cd=50;
			}
			else if(cate=="Special" && bill>200) {
				cd=25;
			}
			bill-=cd;
//			System.out.println("bill is "+bill +" rest offer is "+Restoffer+" fd is "+fd+" and cd is "+cd+" the actual cate is "+cate);
			//food discount
			//Addition of delivery charges
			int del=costumerD(cate);
			deliveryprofit+=del;
			System.out.println("Total order value - INR "+(bill+del)+"/-");
			System.out.println("Delivery charge - "+del+"/-");
			float fin=bill;			// for printing item sucessfully bought for inr 		
			System.out.println("1) Proceed to checkout ");
			Scanner s=new Scanner(System.in);
			int ptco=s.nextInt();
			if(ptco==1) {
				//zotato porfit
				zotatoprofit+=(float)bill/100;
				
				bill+=del;
				
				if(wallet+reward>=bill) {
					bill-=reward;
					wallet-=bill;
					//Adding reward
					int rew=rewardscheme(cart.get(0).rest , bill);		//getting reward
					
					System.out.println(cartno+" items sucessfully bought for INR "+(fin)); /// cost// CORRECT THIS
					reward=0;
					reward=rew;
					restaurant.reward=rew;
				}
				else {
					System.out.println("insufficient balance in wallet");
					for(int i=0;i<cart.size();i++) {
						foodinfo(cart.get(i));
					}
					System.out.println("select the one to delete ");
					int dd=s.nextInt();
					cart.remove(dd-1);
					checkout();
				}
			}
			
		}
		@Override
		public void reward() {
			System.out.println("reward is "+reward);
			
		}
		@Override
		public void recentorders() {
			for(int i=0;i<cart.size();i++) {
				foodinfo(cart.get(i));
			}
		}
		@Override
		public void exit() {
			
			
		}
	}
	
	public static void foodinfo(food current) {
		System.out.println(current.quantity+" "+current.name+" - "+current.name+" "+current.cost+" "+current.quantity+" "+current.offer+"% off "+current.category);
	}
	
	public static class food{
		int id;
		String name;
		String category;
		int cost;
		int quantity;
		int offer;
		String rest;
		Restaurant restaurant;
		food(String name,String cat,int cost,int q,int offer,String rest){
			this.id=counter;
			this.name=name;
			this.category=cat;
			this.cost=cost;
			this.offer=offer;
			this.quantity=q;
			this.rest=rest;
		}
		
	}
	
	public static int costumerD(String cat) {
		if(cat=="Elite") {
			return 0;
		}
		else if(cat=="Special") {
			return 20;
		}
		else {
			return 40;
		}
	}
	
	public static int rewardscheme(String restCat,float bill) {
		if(restCat=="FastFood") {
			int per=(int)bill % 150;
			return per*10;
		}
		else if(restCat=="Authentic restaurants​") {
			int per=(int)bill % 200;
			return per*25;
		}
		else {
			int per=(int)bill % 100;
			return per*5;
		}
	}
	
	public static Restaurant[] reslist=new Restaurant[5];
	public static Costumer[] coslist=new Costumer[5];

	public static void main(String[] args) {
//		Restaurant[] reslist=new Restaurant[5];
		reslist[0]=new Restaurant("Shah","Authentic");
		reslist[1]=new Restaurant("Ravi’s","");
		reslist[2]=new Restaurant("The Chinese","Authentic");
		reslist[3]=new Restaurant("Wang’s","FastFood");
		reslist[4]=new Restaurant("Paradise","");
		
//		Costumer[] coslist=new Costumer[5];
		coslist[0] = new Costumer("Ram","Elite");
		coslist[1] = new Costumer("Sam","Elite");
		coslist[2] = new Costumer("Tim","Special");
		coslist[3] = new Costumer("Kim","");
		coslist[4] = new Costumer("Jim","");
		
		Scanner s=new Scanner(System.in);
		int i=0;
		while(i!=5) {
			main();
			
			int j=s.nextInt();
			
			if(j==1) {
				
				Restaurant rest=mainfirst(reslist);		//Things will be done to this restaurant
				int o=1;
				while(o!=5) {
					System.out.println("Welcome "+rest.resname);
					resowner();
					o=s.nextInt();
					if(o==1) {		// this for which option, add, edit, offer, discount, exit
						rest.AddFoodItem();		//Add food Item method is called on that restaurant
						counter+=1;		//id incremented
					}
					else if(o==2) {
						rest.EditFoodItem();
					}
					else if(o==3) {
						rest.reward();
					}
					else if(o==4) {
						rest.discount();
					}
					else {
						o=5;
					}
				}
			}
			else if(j==2) {
				int op=1;
				Costumer p = personlist(coslist);
				while(op!=5) {		//until 5 is input, this loop goes on
					
					System.out.println("Welcome "+p.name);
					costumermenu();
					op=s.nextInt();
					if(op==1) {
						p.select();
					}
					else if(op==2) {
						p.checkout();
					}
					else if(op==3) {
						p.reward();
					}
					else if(op==4) {
						p.recentorders();
					}
					else {
						op=5;
					}
				}
			}
			else if(j==3) {
				System.out.println("1) Customer List");
				System.out.println("2) Restaurant List");
				int input=s.nextInt();
				if(input==1) {
					Costumer cos=personlist(coslist);
					cosinfo(cos);
				}
				else if(input==2) {
					Restaurant res=mainfirst(reslist);
					resinfo(res);
				}
			}
			else if(j==4) {
				System.out.println("Total Company balance - INR "+zotatoprofit+" /-");
				System.out.println("Total Delivery Charges Collected - INR "+deliveryprofit+" /-");
			}
			else if(j==5) {
				i=5;
			}
		}

	}

}
