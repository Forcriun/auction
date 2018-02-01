import java.util.ArrayList;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael KÃ¶lling.
 * @version 2011.07.31
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }

    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                    lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                    " already has a bid of: " +
                    highestBid.getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {
        Lot lote = null;
        boolean encontrado = false;
        int x = 0;  //Contador del bucle
        
        while(!encontrado && x < lots.size()){
            if(lots.get(x).getNumber() == lotNumber){
                lote = lots.get(x);
                encontrado = true;
            }
            x++;
        }
        
        if(!encontrado) {
            System.out.println("El lote: " + lotNumber + " no existe.");
        }
        
        return lote;
    }

    public void close(){
        for(Lot lot : lots) {
            Bid puja = lot.getHighestBid();
            System.out.println(lot.toString());
            if(puja == null){
                System.out.println("El lote nº " + lot.getNumber() + " no contiene pujas."); 
            }
            else{
                System.out.println("El lote nº " + lot.getNumber() + " tiene una puja más alta de " + puja.getValue() + " realizada por " + puja.getBidder().getName() + ".");                
            }
        }
    }

    public ArrayList<Lot> getUnsold(){
        ArrayList<Lot> lotesSinVender = new ArrayList<>();
        for(Lot lote : lots){
            if(lote.getHighestBid() == null){
                lotesSinVender.add(lote);
            }
        }
        return lotesSinVender;
    }
}
