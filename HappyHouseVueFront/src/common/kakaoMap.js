import http from '@/common/axios.js';

export default {
    mapfunc(positions, map) {
        
        //마커 이미지의 이미지 주소입니다
            var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
            
            for (var i = 0; i < positions.length; i ++) {
                
                // 마커 이미지의 이미지 크기 입니다
                var imageSize = new kakao.maps.Size(24, 35); 
                
                // 마커 이미지를 생성합니다    
                var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
                
                // 마커를 생성합니다
                var marker = new kakao.maps.Marker({
                    map: map, // 마커를 표시할 지도
                    position: positions[i].latlng, // 마커를 표시할 위치
                    title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                    image : markerImage // 마커 이미지 
                });	
            }
        
    },
    
    loadMap(container,x,y,level){
        //지도 
        var mapContainer = container; // 지도를 표시할 div  
        var mapOption = { 
            center: new kakao.maps.LatLng(x, y), // 지도의 중심좌표
            level: level // 지도의 확대 레벨
        };

        var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

        return map;
    },
    setMainMap: function(map){
        let x = 37.50395942713657;
        let y = 127.04282459768903;

        // 마커가 표시될 위치입니다 
        var markerPosition  = new kakao.maps.LatLng(x, y); 

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            position: markerPosition
        });

        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map);

        var iwContent = `<div style="padding:3px 20px;">SSAFY <br><a href="https://map.kakao.com/link/map/멀티캠퍼스,${x},${y}" style="color:blue; font-size:12px" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/멀티캠퍼스,${x},${y}" style="color:blue; font-size:12px" target="_blank">길찾기</a></div>`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
            iwPosition = new kakao.maps.LatLng(x, y); //인포윈도우 표시 위치입니다

        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
            position : iwPosition, 
            content : iwContent 
        });
        
        // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
        infowindow.open(map, marker);
    },
    changeMap: function(map, marker) {
        var moveLoc = marker.latlng;
        console.log(moveLoc);
        map.setCenter(moveLoc);
    },
}

