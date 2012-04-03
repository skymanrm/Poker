package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({StraightFlushTest.class,
	FourOfAKindTest.class,
	FullHouseTest.class,
	FlushTest.class,
	StraightTest.class,
	ThreeOfAKindTest.class,
	TwoPairTest.class,
	PairTest.class,
	HighCardTest.class})
public class AllTests {

}
