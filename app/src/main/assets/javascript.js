function printReceipt(sale) {
    var stringResult = sale.getDate().toString() + '\n' +
    '------------------------------------------' + '\n' +
    sale.getProducts().get(0).getName() + '      ' + sale.getProducts().get(0).getPrice() + '\n' +
    sale.getProducts().get(1).getName() + '      ' + sale.getProducts().get(1).getPrice() + '\n' +
    '------------------------------------------' + '\n' +
    'TOTAL          ' + sale.getPrice() + '\n' +
    '------------------------------------------' + '\n' +
    sale.getId();

    return stringResult;
}