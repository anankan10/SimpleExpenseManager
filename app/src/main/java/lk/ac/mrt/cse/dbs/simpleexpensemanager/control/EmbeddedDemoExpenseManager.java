package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.ExpenseManagerException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.EmbeddedAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.EmbeddedTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class EmbeddedDemoExpenseManager extends ExpenseManager{
    Context context;
    public EmbeddedDemoExpenseManager(Context ctx)  {
        context=ctx;
        try {
            setup();
        } catch (ExpenseManagerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setup() throws ExpenseManagerException {
        SQLiteDatabase database = context.openOrCreateDatabase("170032N", context.MODE_PRIVATE, null);

        // create the databases.
        database.execSQL("CREATE TABLE IF NOT EXISTS Account(" +
                "accountNo VARCHAR PRIMARY KEY," +
                "bankName VARCHAR," +
                "accountHolderName VARCHAR," +
                "balance REAL" +
                " );");

        database.execSQL("CREATE TABLE IF NOT EXISTS Account_Transaction(" +
                "Transaction_id INTEGER PRIMARY KEY," +
                "accountNo VARCHAR," +
                "expenseType INT," +
                "amount REAL," +
                "date DATE," +
                "FOREIGN KEY (accountNo) REFERENCES Account(accountNo)" +
                ");");

        EmbeddedAccountDAO accountDAO = new EmbeddedAccountDAO(database);

        setAccountsDAO(accountDAO);

        setTransactionsDAO(new EmbeddedTransactionDAO(database));

    }
}
