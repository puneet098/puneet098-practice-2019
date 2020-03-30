package com.hacker.earth.programming;

public interface StatisticsAggregator {
	// This is an input. Make note of this price.
				public void putNewPrice(String symbol, double price);

				// Get the average price
				public double getAveragePrice(String symbol);

				// Get the total number of prices recorded
				public int getTickCount(String symbol);
}
