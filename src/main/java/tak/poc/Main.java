package tak.poc;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;


@Slf4j
public class Main {

    public static void main(final String[] args) throws InterruptedException {
        while (true) {
            Thread.sleep(10*100);
            int index = new Random(System.currentTimeMillis()).nextInt(quotes.length);
            String author = quotes[index].author;
            String message = quotes[index].message;

            log
            .atInfo()
            .setMessage(message)
            .addKeyValue("author", author)
            .addKeyValue("index", index)
            .log();
        }
    }



    @AllArgsConstructor
    private static class Quote {
        @Getter private String author;
        @Getter private String message;
    }

    private static final Quote[] quotes = new Quote[] {
            new Quote ("Thomas Edison",		"Genius is one percent inspiration and ninety-nine percent perspiration."),
            new Quote ("Yogi Berra",			"You can observe a lot just by watching."),
            new Quote ("Abraham Lincoln",	"A house divided against itself cannot stand."),
            new Quote ("Johann Wolfgang von Goethe",		"Difficulties increase the nearer we get to the goal."),
            new Quote ("Byron Pulsifer",		"Fate is in your hands and no one elses"),
            new Quote ("Lao Tzu",			"Be the chief but never the lord."),
            new Quote ("Carl Sandburg",		"Nothing happens unless first we dream."),
            new Quote ("Aristotle",			"Well begun is half done."),
            new Quote ("Yogi Berra",			"Life is a learning experience, only if you learn."),
            new Quote ("Margaret Sangster",	"Self-complacency is fatal to progress."),
            new Quote ("Buddha",			    "Peace comes from within. Do not seek it without."),
            new Quote ("Byron Pulsifer",		"What you give is what you get."),
            new Quote ("Iris Murdoch",		"We can only learn to love by loving."),
            new Quote ("Karen Clark",		"Life is change. Growth is optional. Choose wisely."),
            new Quote ("Wayne Dyer",			"You'll see it when you believe it."),
            new Quote ("Unknown",			"Today is the tomorrow we worried about yesterday."),
            new Quote ("Unknown",			"It's easier to see the mistakes on someone else's paper."),
            new Quote ("Unknown",			"Every man dies. Not every man really lives."),
            new Quote ("Lao Tzu",			"To lead people walk behind them."),
            new Quote ("William Shakespeare","Having nothing, nothing can he lose."),
            new Quote ("Henry J. Kaiser",	"Trouble is only opportunity in work clothes."),
            new Quote ("Publilius Syrus",	"A rolling stone gathers no moss."),
            new Quote ("Napoleon Hill",	"Ideas are the beginning points of all fortunes."),
            new Quote ("Donald Trump",	"Everything in life is luck."),
            new Quote ("Lao Tzu",		"Doing nothing is better than being busy doing nothing."),
            new Quote ("Benjamin Spock",	"Trust yourself. You know more than you think you do."),
            new Quote ("Confucius",	    "Study the past, if you would divine the future."),
            new Quote ("Unknown",		"The day is already blessed, find peace within it."),
            new Quote ("Sigmund Freud",	"From error to error one discovers the entire truth."),
            new Quote ("Benjamin Franklin","Well done is better than well said."),
            new Quote ("Ella Williams",	"Bite off more than you can chew, then chew it."),
            new Quote ("Buddha",			   "Work out your own salvation. Do not depend on others."),
            new Quote ("Benjamin Franklin", "One today is worth two tomorrows."),
            new Quote ("Christopher Reeve", "Once you choose hope, anythings possible."),
            new Quote ("Albert Einstein",   "God always takes the simplest way."),
            new Quote ("Charles Kettering", "One fails forward toward success."),
            new Quote ("",			"From small beginnings come great things."),
            new Quote ("Chinese proverb","Learning is a treasure that will follow its owner everywhere"),
            new Quote ("Socrates",		"Be as you wish to seem."),
            new Quote ("V. Naipaul",		"The world is always in movement."),
            new Quote ("John Wooden",	"Never mistake activity for achievement."),
            new Quote ("Haddon Robinson","What worries you masters you."),
            new Quote ("Pearl Buck",		"One faces the future with ones past."),
            new Quote ("Brian Tracy",	"Goals are the fuel in the furnace of achievement."),
            new Quote ("Leonardo da Vinci","Who sows virtue reaps honour."),
            new Quote ("Dalai Lama",		"Be kind whenever possible. It is always possible."),
            new Quote ("Chinese proverb","Talk doesn't cook rice."),
            new Quote ("Buddha",			"He is able who thinks he is able."),
            new Quote ("Larry Elder",	"A goal without a plan is just a wish."),
            new Quote ("Michael Korda",	"To succeed, we must first believe that we can."),
            new Quote ("Albert Einstein","Learn from yesterday, live for today, hope for tomorrow."),
            new Quote ("James Lowell",	"A weed is no more than a flower in disguise."),
            new Quote ("Yoda",			"Do, or do not. There is no try."),
            new Quote ("Harriet Beecher Stowe","All serious daring starts from within."),
            new Quote ("Byron Pulsifer",		"The best teacher is experience learned from failures."),
            new Quote ("Murray Gell-Mann",	"Think how hard physics would be if particles could think."),
            new Quote ("John Lennon",		"Love is the flower you've got to let grow."),
            new Quote ("Napoleon Hill",		"Don't wait. The time will never be just right."),
            new Quote ("Pericles",			"Time is the wisest counsellor of all."),
            new Quote ("Napoleon Hill",	    "You give before you get."),
            new Quote ("Socrates",			"Wisdom begins in wonder."),
            new Quote ("Baltasar Gracian",	"Without courage, wisdom bears no fruit."),
            new Quote ("Aristotle",			"Change in all things is sweet."),
            new Quote ("Byron Pulsifer",		"What you fear is that which requires action to overcome."),
            new Quote ("Cullen Hightower",	"When performance exceeds ambition, the overlap is called success."),
            new Quote ("African proverb",	"When deeds speak, words are nothing."),
            new Quote ("Wayne Dyer",			"Real magic in relationships means an absence of judgement of others."),
            new Quote ("Albert Einstein",	"I never think of the future. It comes soon enough."),
            new Quote ("Ralph Emerson",		"Skill to do comes of doing."),
            new Quote ("Sophocles",			"Wisdom is the supreme part of happiness."),
            new Quote ("Maya Angelou",		"I believe that every person is born with talent."),
            new Quote ("Abraham Lincoln",	"Important principles may, and must, be inflexible."),
            new Quote ("Richard Evans",		"The undertaking of a new action brings new strength."),
            new Quote ("Ralph Emerson",		"The years teach much which the days never know."),
            new Quote ("Ralph Emerson",		"Our distrust is very expensive."),
            new Quote ("Bodhidharma",		"All know the way; few actually walk it."),
            new Quote ("Johann Wolfgang von Goethe",	"Great talent finds happiness in execution."),
            new Quote ("Michelangelo",		"Faith in oneself is the best and safest course."),
            new Quote ("Winston Churchill",	"Courage is going from failure to failure without losing enthusiasm."),
            new Quote ("Leo Tolstoy",		"The two most powerful warriors are patience and time."),
            new Quote ("Lao Tzu",			"Anticipate the difficult by managing the easy."),
            new Quote ("Buddha",			"Those who are free of resentful thoughts surely find peace."),
            new Quote ("Sophocles",			"A short saying often contains much wisdom."),
            new Quote ("Unknown",			"It takes both sunshine and rain to make a rainbow."),
            new Quote ("Unknown",			"A beautiful thing is never perfect."),
            new Quote ("Princess Diana",		"Only do what your heart tells you."),
            new Quote ("John Pierrakos",		"Life is movement-we breathe, we eat, we walk, we move!"),
            new Quote ("Eleanor Roosevelt",	"No one can make you feel inferior without your consent."),
            new Quote ("Richard Bach",		"Argue for your limitations, and sure enough theyre yours."),
            new Quote ("Seneca",			"Luck is what happens when preparation meets opportunity."),
            new Quote ("Napoleon Bonaparte",	"Victory belongs to the most persevering."),
            new Quote ("William Shakespeare","Love all, trust a few, do wrong to none."),
            new Quote ("Richard Bach",		"In order to win, you must expect to win."),
            new Quote ("Napoleon Hill",		"A goal is a dream with a deadline."),
            new Quote ("Napoleon Hill",		"You can do it if you believe you can!"),
            new Quote ("Bo Jackson",			"Set your goals high, and don't stop till you get there."),
            new Quote ("Unknown",			"Every new day is another chance to change your life."),
            new Quote ("Thich Nhat Hanh",	"Smile, breathe, and go slowly."),
            new Quote ("Liberace",			"Nobody will believe in you unless you believe in yourself."),
            new Quote ("William Arthur Ward","Do more than dream: work.")
    };
}