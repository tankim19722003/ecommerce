import { useState } from "react";
import { useParams } from "react-router-dom";

function ProductDetail() {
  const { id } = useParams();
  console.log(id);

  // Giả sử bạn có một hàm gọi API để lấy chi tiết sản phẩm
  const [product, setProduct] = useState(null);

  // if (!product) {
  //   return <Loading />;
  // }

  return (
    <div>
      <h1>Sản phẩm {id}</h1>
    </div>
  );
}

export default ProductDetail;
