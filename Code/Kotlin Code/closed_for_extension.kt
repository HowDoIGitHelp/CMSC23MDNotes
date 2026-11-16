class CreditAccount {
    fun applyMonthlyInterest() {
        println("apply monthly interest to the credit balance")
    }
}

class DebitAccount {
    fun applyMonthlyInterest() {
        println("apply monthly interest to the debit balance")
    }
    fun deactivateIfUnderBalance() {
        println("deactivate if under balance")
    }
}

class PayrollAccount {

}

class Bank {
    val accounts : MutableList<Any> = mutableListOf()

    fun applyAllMonthlyChanges() {
        for (account in accounts) {
            if (account is CreditAccount)
                account.applyMonthlyInterest()
            else if (account is DebitAccount) {
                account.applyMonthlyInterest()
                account.deactivateIfUnderBalance()
            }
        }
    }

    fun enrollAccount(account: Any) {
        accounts.add(account)
    }
}

fun main() {
    val account1 = CreditAccount()
    val account2 = DebitAccount()
    val account3 = PayrollAccount()

    val bank = Bank()
    bank.enrollAccount(account1)
    bank.enrollAccount(account2)
    bank.enrollAccount(account3)

    bank.applyAllMonthlyChanges()
}
