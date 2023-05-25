// function getExif(img1) {
//     EXIF.getData(img1, function() {
//         var exifLong = EXIF.getTag(img1, "GPSLongitude");
//           var exifLat = EXIF.getTag(img1, "GPSLatitude");
//           var exifLongRef = EXIF.getTag(img1, "GPSLongitudeRef");
//           var exifLatRef = EXIF.getTag(img1, "GPSLatitudeRef");
//           //계산식 적용이유는 해당라이브러리가 위도경도를 도분초 단위로 출력하기 때문
//         var latitude;
//         var longitude;
//             if (exifLatRef == "S") {
//             latitude = exifLat[0] * -1 + (exifLat[1] * -60 + exifLat[2] * -1) / 3600;
//           } else {
//             latitude = exifLat[0] + (exifLat[1] * 60 + exifLat[2]) / 3600;
//           }
//           if (exifLongRef == "W") {
//            longitude = exifLong[0] * -1 + (exifLong[1] * -60 + exifLong[2] * -1) / 3600;
//           } else {
//             longitude = exifLong[0] + (exifLong[1] * 60 + exifLong[2]) / 3600;
//           }
//           console.lof(latitude);
//           console.lof(longitude);
//     });
// }

// export {getExif};