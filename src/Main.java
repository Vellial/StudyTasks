//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

//    MyStringBuilder sb = new MyStringBuilder();
//
//    sb.append("Hello");
//    IO.println(sb);
//
//    sb.append("World!");
//    IO.println(sb);
//
//    sb.undo();
//    IO.println(sb);

    DataAggregator aggregator = new DataAggregator();

    ProductInfo productInfo = aggregator.aggregateProductInfo("Notebook");

    System.out.println("productInfo = " + productInfo);
}
