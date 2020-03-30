package com.hacker.earth.programming;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

		public class StatisticsAggregatorImpl implements StatisticsAggregator {
			
			private static Map<String,Double> priceMap = new HashMap<String,Double>();
			private static Map<String,Integer> tickermap = new HashMap<String,Integer>();
			
			
			public void putNewPrice(String symbol, double price) {				
				if(priceMap.containsKey(symbol) && tickermap.containsKey(symbol)){
					double lastPrice = priceMap.get(symbol);
					priceMap.put(symbol, price+lastPrice);
					int ticker = tickermap.get(symbol);
					tickermap.put(symbol, ++ticker);
				}else {
					priceMap.put(symbol, price);
					tickermap.put(symbol, 1);
				}
			}

			public double getAveragePrice(String symbol) {
				if(priceMap.containsKey(symbol) && tickermap.containsKey(symbol)){
					double lastPrice = priceMap.get(symbol);
					int ticker = tickermap.get(symbol);
					return (lastPrice/ticker);
				}
				return 0.0;
			}


			public int getTickCount(String symbol) {
				if(tickermap.containsKey(symbol)) {
					 return tickermap.get(symbol);
				}else {
					return 0;
				}
				
			}
		
	

		

			public static void main (String[] args) {

				try {
					Scanner scanner = new Scanner(System.in);
					while (scanner.hasNext()) {
						StatisticsAggregator stats = new StatisticsAggregatorImpl();
						Set<String> symbols = new TreeSet<String>();

						String line = scanner.nextLine();
						String[] inputs = line.split(",");
						for (int i = 0; i < inputs.length; ++i) {
							String[] tokens = inputs[i].split(" ");
							String symbol = null;
							double price=0.0;
							if(tokens.length >= 2) {
								for(int j = 0; j < tokens.length; j++) {
									if((!(tokens[j].contentEquals(""))) && (symbol==null)) {
										symbol = tokens[j].trim();
										symbols.add(symbol);
									}else if((!(tokens[j].contentEquals(""))) && (symbol!=null) && (price == 0.0)) {
										price = Double.parseDouble(tokens[j].trim());
									}else if((symbol!=null) && (price != 0.0)) {
										break;
									}
									System.out.println("next itr");
								}
							}
							System.out.println(symbol);
							stats.putNewPrice(symbol, price);

						}

						for (String symbol : symbols) {
							System.out.println(
									String.format("%s %.4f %d", symbol, stats.getAveragePrice(symbol), stats.getTickCount(symbol)));
						}
					}
					scanner.close();

				}catch(Exception ex) {
					ex.printStackTrace();
				}

			}
	}

