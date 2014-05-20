import java.util.ArrayList;


/**
 * Minimum Polygon class for Android.
 */
public class Polygon {

    // Polygon coodinates.
    private int[] polyY, polyX;

    // Number of sides in the polygon.
    private int polySides;

    /**
     * Default constructor.
     * @param px Polygon y coods.
     * @param py Polygon x coods.
     * @param ps Polygon sides count.
     */
    public Polygon( int[] px, int[] py, int ps ) {

        polyX = px;
        polyY = py;
        polySides = ps;
    }

    /**
     * Checks if the Polygon contains a point.
     * @see "http://alienryderflex.com/polygon/"
     * @param x Point horizontal pos.
     * @param y Point vertical pos.
     * @return Point is in Poly flag.
     */
    public boolean contains( int x, int y ) {

        boolean oddTransitions = false;
        for( int i = 0, j = polySides -1; i < polySides; j = i++ ) {
            if( ( polyY[ i ] < y && polyY[ j ] >= y ) || ( polyY[ j ] < y && polyY[ i ] >= y ) ) {
                if( polyX[ i ] + ( y - polyY[ i ] ) / ( polyY[ j ] - polyY[ i ] ) * ( polyX[ j ] - polyX[ i ] ) < x ) {
                    oddTransitions = !oddTransitions;          
                }
            }
        }
        return oddTransitions;
    }   
    
    /*
     * TGISObject to1 = new TGISObject();
		to1.ObjectId= "1";
		TGISPoints points = new TGISPoints();
		TGISPoint pt1 = new TGISPoint(5,5,0);
		TGISPoint pt2 = new TGISPoint(40,5,0);
		TGISPoint pt3 = new TGISPoint(5,60,0);
		points.Add(pt1);
		points.Add(pt2);
		points.Add(pt3);
		to1.setPoints(points);
		objectList.add(to1);
		
		TGISObject to2 = new TGISObject();
		to2.ObjectId="2";
		TGISPoints points2 = new TGISPoints();
		TGISPoint pt21 = new TGISPoint(60,5,0);
		TGISPoint pt22 = new TGISPoint(120,5,0);
		TGISPoint pt23 = new TGISPoint(85,60,0);
		TGISPoint pt24 = new TGISPoint(25,60,0);
    */
    public static void main(String[] args) {  
        int[] px = {60,120,85,25};
        int[] py = {5,5,60,60};
        int ps = 4 ;
        Polygon poly = new Polygon(px,py,ps);
        boolean bl = poly.contains(60,40);
        System.out.println(bl);
    }  
}