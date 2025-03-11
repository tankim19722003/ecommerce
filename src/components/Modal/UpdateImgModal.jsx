import React, { useState } from "react";
import axios from "axios";
import { useTheme } from "../../Provider/ThemeProvider";
import { Cloudinary } from "@cloudinary/url-gen";
import { auto } from "@cloudinary/url-gen/actions/resize";
import { autoGravity } from "@cloudinary/url-gen/qualifiers/gravity";
import { AdvancedImage } from "@cloudinary/react";

const UpdateImgModal = ({ onCloseUpdateImgModal }) => {
  const [image, setImage] = useState(null);
  const [preview, setPreview] = useState(null);
  const [uploadedImageUrl, setUploadedImageUrl] = useState("");
  const { isDarkMode } = useTheme();

  const handleImageChange = (event) => {
    const file = event.target.files[0];
    if (file) {
      setImage(file);
      setPreview(URL.createObjectURL(file)); // Hiển thị ảnh trước khi upload
    }
  };

  const handleUpload = async () => {
    if (!image) {
      alert("Vui lòng chọn ảnh!");
      return;
    }

    const formData = new FormData();
    formData.append("file", image);
    formData.append("upload_preset", "YOUR_UPLOAD_PRESET"); // Thay thế bằng upload_preset của bạn

    try {
      const response = await axios.post(
        "https://api.cloudinary.com/v1_1/dmpfvill9/image/upload",
        formData
      );

      const imageUrl = response.data.secure_url;
      setUploadedImageUrl(imageUrl); // Lưu URL ảnh sau khi upload
      console.log("Ảnh đã upload thành công:", imageUrl);
    } catch (error) {
      console.error("Lỗi khi upload ảnh:", error);
    }
  };

  const cld = new Cloudinary({ cloud: { cloudName: "dmpfvill9" } });

  // Use this sample image or upload your own via the Media Explorer
  const img = cld
    .image("cld-sample-5")
    .format("auto") // Optimize delivery by resizing and applying auto-format and auto-quality
    .quality("auto")
    .resize(auto().gravity(autoGravity()).width(500).height(500)); // Transform the image: auto-crop to square aspect_ratio

  return (
    <div
      className="fixed top-0 bottom-0 left-0 right-0 flex items-center justify-center bg-[#2e2e2e27] z-[100]"
      onClick={onCloseUpdateImgModal}
    >
      <div
        className={`w-[30%]  flex flex-col px-[20px] py-[10px] gap-[20px]  rounded-[5px] ${
          isDarkMode ? "text-dark-100 bg-white" : "text-white bg-dark-400"
        }`}
        onClick={(e) => e.stopPropagation()}
      >
        <input type="file" accept="image/*" onChange={handleImageChange} />
        {preview && (
          <img src={preview} alt="Preview" style={{ width: "200px" }} />
        )}
        <button className="outline-none bg-primary" onClick={handleUpload}>
          Upload
        </button>
        {uploadedImageUrl && (
          <div>
            <h3>Ảnh đã upload:</h3>
            <img
              src={uploadedImageUrl}
              alt="Uploaded"
              style={{ width: "200px" }}
            />
          </div>
        )}
      </div>
    </div>
  );
};

export default UpdateImgModal;
