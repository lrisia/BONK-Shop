# cs211-641-project

## รายชื่อสมาชิก
* 6310450069 วริทธิ์ วัฒนกฤษฎา (MydudeEiEi)
    * 13.ส่วนของ GUI ที่แสดงถึงข้อมูลต่อไปนี้ โดยอาจเข้าถึงข้อมูลนี้ผ่านเมนู หรือมีปุ่มกดที่หน้าแรก
    * 17.ระบบสำหรับผู้ซื้อสินค้า(ใช้ระบบที่ลงทะเบียนตามข้อ 15.1 จะเป็นผู้ซื้อสินค้าโดยปริยาย ซึ่งจะเข้าดูหน้า marketplace และเลือกซื้อสินค้าได้)
      * 17.1 หน้า marketplace ที่แสดงสินค้าทั้งหมดจากทุกร้าน เรียงลำดับโดยให้สินค้าที่วางขายล่าสุดขึ้นแสดงก่อน โดยแสดงภาพสินค้า ชื่อสินค้า และราคาสินค้า
      * 17.3 หน้า marketplace ที่แสดงสินค้าทั้งหมดจากทุกร้าน มีส่วนให้ผู้ซื้อเลือกการแสดงผลเฉพาะสินค้าตามหมวดหมู่ที่สนใจ
    * 18.ความสวยงามและประสบการณ์ของผู้ใช้งาน
      * 18.1 ใช้โทนสีและองค์ประกอบต่าง ๆ ของ GUI ที่ดูได้ชัดเจน น่าใช้ ไม่แสบตา ไม่ลวงตา โดยลองนึกถึงว่าหากเราทำโปรแกรมนี้ให้คนทั่วไปใช้จริง ควรแสดงองค์ประกอบเหล่านี้อย่างไร
      * 18.3 Graphic User Interface (GUI) ควรมีรูปแบบที่เข้าใจง่าย ขนาดหน้าจอของโปรแกรมต้องมีความสูงไม่เกิน 1024 pixel
    * 19.โปรแกรมต้องสามารถบันทึกสถานะของข้อมูลต่าง ๆ ในรูปแบบของไฟล์ csv และสามารถโหลดไฟล์ที่บันทึกไว้นั้นมาแสดงผลได้อย่างถูกต้อง
      * 19.1 ต้องมีการบันทึกสถานะและข้อมูลที่จำเป็นในรูปแบบไฟล์ csv
      * 19.2 เมื่อเปิดโปรแกรมใหม่ หรือเมื่อโปรแกรมโหลดไฟล์ csv จะต้องแสดงข้อมูลที่ได้บันทึกไว้อย่างถูกต้อง

  
* 6310451294 พชร สุวราวรนาถ (Irisia)
  * 14.เขียนโปรแกรมในส่วนของผู้ดูแลระบบ
    * 14.1 ผู้ดูแลระบบสามารถเปลี่ยนรหัสผ่านของตนเองได้ และรหัสผ่านใหม่ต้องใช้ได้
    * 14.2 หน้าแสดงรายชื่อของผู้ใช้ระบบ โดยต้องแสดงภาพผู้ใช้ username ชื่อของผู้ใช้ระบบ ชื่อร้านค้า (กรณีผู้ใช้ระบบเปิดร้านค้าของตนเอง) และวันเวลาที่เข้าใช้ล่าสุดของผู้ใช้ระบบคนนั้น เรียงลำดับตามวันเวลาที่ผู้ใช้ระบบเข้าใช้งานล่าสุดก่อน
    * 14.3 หน้าแสดงรายการของรายงานความไม่เหมาะสมของผู้ใช้ระบบ
  * 15.สร้างบัญชีของผู้ใช้ระบบ
    * 15.1 ระบบลงทะเบียน
    * 15.2 ผู้ใช้ระบบสามารถเปลี่ยนรหัสผ่านของตนเองได้ และรหัสผ่านใหม่
  * 16. ระบบสำหรับผู้ขายสินค้า
    * 16.4 หน้ารายการสินค้าทั้งหมด สามารถเลือกสินค้าเพื่อแก้ไขข้อมูลสินค้าได้ โดยสามารถแก้ไขภาพสินค้า ชื่อสินค้า ราคาสินค้า ได้ถูกต้อง มีการตรวจสอบรูปแบบของข้อมูลก่อนจะบันทึกการแก้ไข
    * 16.5 หน้ารายการสินค้าทั้งหมด สามารถเลือกสินค้าเพื่อเพิ่มจำนวนสินค้าได้ จำนวนสินค้าจะต้องเพิ่มขึ้นอย่างถูกต้อง
    * 16.6 เมนูตั้งค่าร้านค้า กำหนดจำนวนที่ถือว่าสินค้ามีจำนวนน้อยในคลัง
    * 16.7 มีเมนูดูรายการสั่งซื้อ แสดงรายการสินค้าที่มีผู้ซื้อสั่งซื้อ แยกรายการสั่งซื้อใหม่ และรายการสั่งซื้อที่จัดส่งแล้ว โดยแสดงภาพสินค้า ชื่อสินค้า จำนวนสินค้า ยอดซื้อ และมีส่วนของการจัดส่งสินค้า ซึ่งจะสมมติว่าผู้ขายส่งสินค้าเรียบร้อยแล้ว โดยต้องระบุหมายเลขติดตาม (tracking number) ในรายการสั่งซื้อ จากนั้นต้องลดจำนวนสินค้าได้ถูกต้อง และรายการสั่งซื้อจะเปลี่ยนสถานะเป็นจัดส่งแล้
  * 17.ระบบสำหรับผู้ซื้อสินค้า(ใช้ระบบที่ลงทะเบียนตามข้อ 15.1 จะเป็นผู้ซื้อสินค้าโดยปริยาย ซึ่งจะเข้าดูหน้า marketplace และเลือกซื้อสินค้าได้)
    * 17.2 หน้า marketplace ที่แสดงสินค้าทั้งหมดจากทุกร้าน มีส่วนให้ผู้ซื้อเลือกเรียงลำดับสินค้าจากราคามากที่สุดขึ้นแสดงก่อน หรือเลือกเรียงลำดับสินค้าจากราคาน้อยที่สุดขึ้นแสดงก่อน
    * 17.3 เลือกเรียงลำดับการแสดงสินค้าจากราคาข้อ 17.2 ได้
    * 17.4 หน้า marketplace ที่แสดงสินค้าทั้งหมดจากทุกร้าน มีส่วนให้ผู้ซื้อเลือกการแสดงผลเฉพาะสินค้าที่มีช่วงราคาที่ผู้ซื้อกำหนดได้เอง โดยยังคงเลือกเรียงลำดับการแสดงสินค้าจากราคาข้อ 17.2 ได้
    * 17.6 ผู้ซื้อเลือกซื้อสินค้าจากหน้า marketplace แล้วจากข้อ 17.5 สามารถสร้างรายการสั่งซื้อได้เลย (ไม่ต้องทำระบบตะกร้าสินค้า) โดยให้ระบุจำนวน และมีการสรุปราคาให้ก่อนยืนยันการสร้างรายการสั่งซื้อ ซึ่งจะมีสถานะเป็นรายการสั่งซื้อใหม่ หากจำนวนสินค้ามีไม่เพียงพอ จะต้องมีข้อความแจ้งเตือนเพื่อให้ผู้ซื้อทราบอย่างเหมาะสม
    * 17.8 ในหน้ารายละเอียดสินค้า ข้อ 17.5 มีส่วนที่ให้ผู้ซื้อแจ้งรายงานความไม่เหมาะสมของสินค้า ซึ่งจะไปแสดงผลในข้อ 14.3 โดยต้องระบุประเภทของความไม่เหมาะสม และข้อความเพิ่มเติม ซึ่งเจ้าของร้านอาจถูกระงับสิทธิ์การเข้าใช้ระบบจากรายงานนี้เมื่อผู้ดูแลระบบเห็นว่าไม่เหมาะสมจริง
  * 18.ความสวยงามและประสบการณ์ของผู้ใช้งาน
    * 18.1 ใช้โทนสีและองค์ประกอบต่าง ๆ ของ GUI ที่ดูได้ชัดเจน น่าใช้ ไม่แสบตา ไม่ลวงตา โดยลองนึกถึงว่าหากเราทำโปรแกรมนี้ให้คนทั่วไปใช้จริง ควรแสดงองค์ประกอบเหล่านี้อย่างไร
  * 19.โปรแกรมต้องสามารถบันทึกสถานะของข้อมูลต่าง ๆ ในรูปแบบของไฟล์ csv และสามารถโหลดไฟล์ที่บันทึกไว้นั้นมาแสดงผลได้อย่างถูกต้อง
    * 19.1 ต้องมีการบันทึกสถานะและข้อมูลที่จำเป็นในรูปแบบไฟล์ csv
    * 19.2 เมื่อเปิดโปรแกรมใหม่ หรือเมื่อโปรแกรมโหลดไฟล์ csv จะต้องแสดงข้อมูลที่ได้บันทึกไว้อย่างถูกต้อง


* 6310451871 อนวัช หอมหวล (Anawat223)  
  * 14.เขียนโปรแกรมในส่วนของผู้ดูแลระบบ
    * 14.4 มีการตรวจสอบรูปแบบของข้อมูลก่อนจะบันทึกการลงขายสินค้า Muad
  * 15.สร้างบัญชีของผู้ใช้ระบบ
    * 15.3 ผู้ใช้ระบบ upload รูปภาพ เพื่อใช้เป็นภาพของผู้ใช้ระบบ และสามารถเปลี่ยนรูปได้ หากผู้ใช้ระบบยังไม่กำหนดภาพ ระบบจะให้ใช้ภาพ default
  * 16.ระบบสำหรับผู้ขายสินค้า
    * 16.1 เมนูเปิดร้านค้า ให้ผู้ใช้ระบบสามารถสร้างร้านค้าของตนเอง ซึ่งจะทำให้ผู้ใช้ระบบมีสถานะเป็นผู้ขายด้วย โดยการเปิดร้านค้าจะใช้ข้อมูลชื่อร้าน
    * 16.2 เมนูเพิ่มสินค้า เพื่อลงขายสินค้าโดยใช้ข้อมูลชื่อสินค้า ภาพสินค้า รายละเอียดสินค้า ราคาสินค้า จำนวนสินค้าในคลัง
    * 16.3 เมนูดูรายการสินค้าทั้งหมด ที่แสดงรายการสินค้าทั้งหมด แสดงภาพสินค้า ชื่อสินค้า ราคาสินค้า และจำนวนสินค้าในคลัง
  * 17.ระบบสำหรับผู้ซื้อสินค้า(ใช้ระบบที่ลงทะเบียนตามข้อ 15.1 จะเป็นผู้ซื้อสินค้าโดยปริยาย ซึ่งจะเข้าดูหน้า marketplace และเลือกซื้อสินค้าได้)
    * 17.5  เมื่อผู้ซื้อกดเลือกสินค้าจากหน้า marketplace แล้ว ให้แสดงหน้ารายละเอียดสินค้า โดยแสดงข้อมูลสินค้าที่ผู้ขายกรอกข้อมูลการวางขายสินค้าข้อ 16.2
    * 17.6 ผู้ซื้อเลือกซื้อสินค้าจากหน้า marketplace แล้วจากข้อ 17.5 สามารถสร้างรายการสั่งซื้อได้เลย (ไม่ต้องทำระบบตะกร้าสินค้า) โดยให้ระบุจำนวน และมีการสรุปราคาให้ก่อนยืนยันการสร้างรายการสั่งซื้อ ซึ่งจะมีสถานะเป็นรายการสั่งซื้อใหม่ หากจำนวนสินค้ามีไม่เพียงพอ จะต้องมีข้อความแจ้งเตือนเพื่อให้ผู้ซื้อทราบอย่างเหมาะสม
  * 18.ความสวยงามและประสบการณ์ของผู้ใช้งาน
    * 18.1 ใช้โทนสีและองค์ประกอบต่าง ๆ ของ GUI ที่ดูได้ชัดเจน น่าใช้ ไม่แสบตา ไม่ลวงตา โดยลองนึกถึงว่าหากเราทำโปรแกรมนี้ให้คนทั่วไปใช้จริง ควรแสดงองค์ประกอบเหล่านี้อย่างไร
    * 18.3 Graphic User Interface (GUI) ควรมีรูปแบบที่เข้าใจง่าย ขนาดหน้าจอของโปรแกรมต้องมีความสูงไม่เกิน 1024 pixel 
  * 19.โปรแกรมต้องสามารถบันทึกสถานะของข้อมูลต่าง ๆ ในรูปแบบของไฟล์ csv และสามารถโหลดไฟล์ที่บันทึกไว้นั้นมาแสดงผลได้อย่างถูกต้อง
    * 19.1 ต้องมีการบันทึกสถานะและข้อมูลที่จำเป็นในรูปแบบไฟล์ csv
    * 19.2 เมื่อเปิดโปรแกรมใหม่ หรือเมื่อโปรแกรมโหลดไฟล์ csv จะต้องแสดงข้อมูลที่ได้บันทึกไว้อย่างถูกต้อง


## วิธีการติดตั้งหรือรันโปรแกรม
(ระบุวิธีการติดตั้งระบบ เช่น งานโปรเจคที่สำเร็จอยู่ที่ directory ใด ต้องใช้ command เพื่อเปิดหน้าโปรแกรมอย่างไร)


## การวางโครงสร้างไฟล์

## ตัวอย่างข้อมูลผู้ใช้ระบบ
* (Seller) (Username: Military) (Password: hailhydra)
* (Seller) (Username: JohnApexShop) (Password: 212224236)
* (Seller) (Username: Trevor007) (Password: HelloWorld)
* (User) (username: SamZaa) (password: 108041998Sam)
* (User) (username: หญิงเอง) (password: 18145)
* (User) (username: KittyCat123) (password: meowmeow)
* (User) (username: OneForAll) (password: NumberOneHero)
* (User) (username: IronManZaa007) (password: Tonylovepepper)

## สรุปสิ่งที่พัฒนาแต่ละครั้งที่นำเสนอความก้าวหน้าของระบบ
* ครั้งที่ 1 (วันที่เสนอ)
  * (สรุปสิ่งที่พัฒนา) (พัฒนาโดยใคร)
  * (สรุปสิ่งที่พัฒนา) (พัฒนาโดยใคร)
  * (สรุปสิ่งที่พัฒนา) (พัฒนาโดยใคร)
  * (สรุปสิ่งที่พัฒนา) (พัฒนาโดยใคร)
* ครั้งที่ 2 (วันที่เสนอ)
  * (สรุปสิ่งที่พัฒนา) (พัฒนาโดยใคร)
  * (สรุปสิ่งที่พัฒนา) (พัฒนาโดยใคร)
  * (สรุปสิ่งที่พัฒนา) (พัฒนาโดยใคร)
  * (สรุปสิ่งที่พัฒนา) (พัฒนาโดยใคร)
* ครั้งที่ 3 (วันที่เสนอ)
  * (สรุปสิ่งที่พัฒนา) (พัฒนาโดยใคร)
  * (สรุปสิ่งที่พัฒนา) (พัฒนาโดยใคร)
  * (สรุปสิ่งที่พัฒนา) (พัฒนาโดยใคร)
  * (สรุปสิ่งที่พัฒนา) (พัฒนาโดยใคร)  