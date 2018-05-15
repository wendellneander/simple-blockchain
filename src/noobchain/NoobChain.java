package noobchain;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class NoobChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;

	public static void main(String[] args) {
		
		blockchain.add(new Block("Hi im the first block", "0"));
		System.out.println("Trying to Mine Block 1...");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block("Hi im the second block", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine Block 2...");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("Hi im the third block", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine Block 3...");
		blockchain.get(2).mineBlock(difficulty);
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		/*blockchain.add(new Block("Yo im the first block", "0"));
		blockchain.add(new Block("Yo im the first block", blockchain.get(blockchain.size()-1).hash));
		blockchain.add(new Block("Yo im the first block", blockchain.get(blockchain.size()-1).hash));*/
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe Blockchain: ");
		System.out.println(blockchainJson);
	}
	
	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		
		for(int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			
			if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes not equal");
				return false;
			}
			
			if(!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous Hashes not equals");
				return false;
			}
			
		}
		
		return true;
	}

}
