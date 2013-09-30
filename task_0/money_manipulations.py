class MoneyManager(object):
    """ A money manager class for transferring money between accounts
    """

    def transfer_money(self, from_, to, amount):
        """ Transfer an amount of money from one bank account to another

        @param from: the account object where from an amount is subtracted
        @param to: the account object where to an amount is added
        @param amount: the amount of money that is being transfered
        """
        from_.sub(amount)
        to.add(amount)

    def sub(self, from_, amount):
        """ Extract an amount of money from a user bank account

        @param from_: the user bank account object where from the money will be
                      subtracted
        @param amount: the amount of money to be subtracted
        """
        from_.amount -= amount

    def add(self, to, amount):
        """ Add an amount of money to user bank account

        @param to: the user bank account object where to the money will be transfered
        @param amount: the amount of money to be added
        """
        to.amount += amount


class UserBankAccount(object):
    """ Simplistic class that represents a user account in a bank
    """

    def __init__(self, amount):
        """ Initialize user's bank account

        @param amount: the amount of money of the user
        """
        self.amount = amount
