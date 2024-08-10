document.addEventListener('DOMContentLoaded', () => {
    const MAX_SELECTION = 2; // 最大選択数
    let selectedImages = [];

    function toggleSelect(element, imageName) {
        const img = element.querySelector('img');
        const imagePath = img.src;

        if (selectedImages.includes(imageName)) {
            selectedImages = selectedImages.filter(name => name !== imageName);
            element.classList.remove('selected');
        } else {
            if (selectedImages.length >= MAX_SELECTION) {
                return;
            }
            selectedImages.push(imageName);
            element.classList.add('selected');
        }

        // 選択した画像の名前をフォームのinputに設定
        document.getElementById('selectedImages').value = selectedImages.join(',');
    }

    // クリックイベントリスナーを設定
    document.querySelectorAll('.group-item').forEach(item => {
        item.addEventListener('click', function() {
            const imageName = this.querySelector('img').src.split('/').pop(); // 画像名を取得
            toggleSelect(this, imageName);
        });
    });
});
