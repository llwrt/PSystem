package math;

public class GravityVector extends Vector2D{

	public GravityVector(){
		super(0, .3);
	}
	
	public GravityVector(int power){
		super(0, .3*power);
	}
	
}
