CREATE DATABASE  IF NOT EXISTS `app` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `app`;
-- MySQL dump 10.13  Distrib 5.5.46, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: app
-- ------------------------------------------------------
-- Server version	5.5.46-0ubuntu0.14.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blog_post`
--

DROP TABLE IF EXISTS `blog_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` mediumtext,
  `cover_link` varchar(255) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_ts` datetime DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `lead` varchar(1024) DEFAULT NULL,
  `intro` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_post`
--

LOCK TABLES `blog_post` WRITE;
/*!40000 ALTER TABLE `blog_post` DISABLE KEYS */;
INSERT INTO `blog_post` VALUES (1,'<!-- Post Content -->\n                <p class=\"lead\">Breakfree into natures lap @Sandakphu</p>\n\n                <p><i>Sandakphu</i> the highest peak of west Bengal is situated at the edge of the Singalila National Park in\n                Darjeeling district on the West Bengal-Nepal border, and is the highest point of the Singalila Ridge. Kangchenjunga, Lhotse and Makalu can be seen\n                from its summit. </p>\n                \n                <p>When I looked at the calendar and found 2 consecutive holidays on Thursday and Friday, the first thing that popped up my mind was\n                to plan for a short trip. A trek plan in four days sounds impossible but my love for this place forced me to tweak it somewhere and go for it.\n                Normally it takes 5 days (3 days uphill trek and 2 days downhill trek) to reach <i>Sandakphu</i> but there is also a possibility to hire Land Rover\n                vehicles as well. So our plan was <br/>\n                <ul>\n                    <li>Day 1 (Wednesday night after office): Board train at <i>Sealdah</i> to <i>New Jalpaiguri</i></li>\n                    <li>Day 2: <i>New Jalpaiguri</i> to Dhotre and then trek 5 kilometers to <i>Tonglu</i></li>\n                    <li>Day 3: <i>Tonglu</i> to <i>Sandakphu</i> (hiring Land Rover Jeeps to cover some part of the road)</li>\n                    <li>Day 4: <i>Sandakphu</i> to <i>Srikhola</i></li>\n                    <li>Day 5: <i>Srikhola</i> to <i>New Jalpaiguri</i> via <i>Rimbik</i></li>\n                    <li>Day 6: Board train from <i>New Jalpaiguri</i> and reach <i>Sealdah</i> in the morning and join Office :(</li>\n                </ul>\n                </p>\n\n        <p class=\"lead\">Day 1: <i>Sealdah</i> to <i>New Jalpaiguri</i></p>\n        <p>Take a night train from <i>Sealdah</i> Railway station to <i>New Jalpaiguri</i> (NJP). There are quite a few trains but Darjeeling Mail and Padatik Express are\n        the most preferred one. We were four enthusiasts and our train, Padatik Express departed from <i>Sealdah</i> on its scheduled time at 10:55 pm.</p>\n\n        <p class=\"lead\">Day 2: <i>New Jalpaiguri</i> to <i>Tonglu</i> Top</p>\n        We arrived NJP by 9:00 am in the morning. We went to <b>Darjeeling More</b> by auto rickshaw which costs around Rs. 30 per person. From there we\n        booked a shared trekker (cab) which took us to Sukhiapokhri (Rs. 140 per person). We arrived there at around 1:30 pm. From there we hired an Omni\n        for Rs 1500 which took us to Dhotre (should have bargained here, as the reasonable cost seems to be around 1k). We reached Dhotre at around 3:30 pm.\n        There are 2 trekking routes to <i>Sandakphu</i>. One starts from <i>Manebhanjan</i> which is 31 km and the other shorter route from <i>Dhotre</i> (27 km).\n        The route via Dhotre passes through the Dense Singalilia forest. Both the routes meet at Tumling a small Hamlet in Nepal and takes 3 days to reach\n        <i>Sandakphu</i> normally. The entire trail to <i>Sandakphu</i> is along the borders of India and Nepal. One has to take along a guide to enter the Singalilia\n        National Park. We chose to trek from Dhotre since we were already late. Even the local people suggested us to take rest at Dhotre overnight and\n        start the next day but for a Software Engineer the most precious thing is <i>Leaves</i> and spending the night at Dhotre would mean that the plan to\n        <i>Sandakphu</i> would be in jeopardy. So we decided to start withing 10 mins of reaching here and had just Momos in lunch. We booked a guide from the\n        local Guide Office at Dhotre. Since it was already late and it\'s quite risky to complete the trek to <i>Tonglu</i> at night, we are given a very\n        experienced guide.\n\n        <p>The rate for hiring a guide was Rs. 550 per day. We started our trek at 3:40 pm and soon we entered the dense park. The route is well marked and\n        hardly any chance of wandering into the forest.As soon we have coverd hardly half kilometers,we saw two gentelman with another guide resting their\n        for minute.We are happy to get another three people for the journey.Our guide provided us some bamboo sticks,which helped us throughout our\n        journey!It was 5:30pm when the sun had already set but still the daylight kept us going till we reached <i>Tonglu</i> top at 6:00 pm. the guide said that\n        at this time of year days are longer for which we could easliy make it to <i>Tonglu</i> top before dusk.By the time we reached there ,weather started to\n        become chilly with bone-chilling winds. we got into the trekkers hut booked earlier by our guide.the charge was rs.130 per bed but it was worth it\n        because the interiors was awesome with windows facing the mighty kanchenjunga. We had dinner as there people have dinner early at 7:00 pm(Rs.150 per\n        meal).After having dinner we got into our room provided with some candles which was not enough for us but we were helped by our natural satelite\n        Moon. As it was a full moon night we hardly needed any candle because the silver rays were enough. Savouring the natural beauty we went to sleep but\n        at midnight we got to witness some breathtaking scenes which was ofcourse was the snow covered kanchenjunga shining in the silver rays of moon with\n        stars adding up to its shimmering beauty.</p>\n\n\n\n        <div class=\"travelImg\">\n            <img alt=\"Gopal Chettri\" src=\"https://lh6.googleusercontent.com/-tLpcyXaFasg/VQRcQZRDb5I/AAAAAAAACzc/4DQ_TO3DFpI/w1341-h894-no/DSC_0183.JPG\">\n        </div>\n\n\n\n\n        <br />\n        <p class=\"lead\">Day 3: <i>Tonglu</i> to <i>Sandakphu</i> (<i>Garibash</i> to Bikheybhanjang on Land Rover vehicle)</p>\n\n        <div class=\"travelImg\">\n            <a href=\"https://lh3.googleusercontent.com/ZTDNaIEGV2kVLqhUrkElLbRShGqg7XOe2IkXkQmBhVU=w1341-h894-no\"> <img alt=\"Sleeping Buddha\"\n                src=\"https://lh3.googleusercontent.com/ZTDNaIEGV2kVLqhUrkElLbRShGqg7XOe2IkXkQmBhVU=w338-h225-p-no\">\n            </a>\n        </div>\n        <br>\n        <p>One must get up early at 5:00 am to witness the changing beauty of Kanchenjunga from lying quiet in the silver rays of moonlight to shining in the\n        golden rays of Sun, the so called \"golden peak\". As the trek for the third day was long so we started early after having breakfast to reach\n        <i>Sandakphu</i> within time, passing another settlement - Tunmling. We got our permission from a checkpost to enter Singalila National forest. Few\n        kilometer on a gentle downhill there lies <i>Jaubari</i> (Nepal). The route to <i>Sandakphu</i> turns right from here along the Singalilia ridge which\n        coincidentally is also the border of Nepal and India. We shared momos and thuppa here. Four kilometers downhill from <i>Jaubari</i> takes us to <i>Garibash</i>.\n        The route was not that tough as it was a wide one with ups and downs to reach <i>Garibash</i>. We reached <i>Garibash</i> at around 11:00 am, had Roti and Sabji\n        in lunch. Since we had a day short in our plan, we were forced to cover a few kilometers on Jeep to save some time. So we booked a Jeep which took\n        us to Bikheybhanjang (Bikhey - a poisinous herb, bhanjang - a valley) beyond which it was not possible for even the Land Rovers to move. On the way\n        we passed Kalapokhri (Kala - Black, Pokhri - Pong) another settlement from there the weather started to become foggy and chilly, visibility reduced\n        to 1-2 meters. But our driver was experienced one and he was driving as if playing NFS !!! with all the turns within his mind and in our mind ? GOD\n        ofcourse. Co-incidentally it was the most difficult part of the trek which we covered on Land Rover :(\n        </p>\n        <div class=\"travelImg\">\n            <a href=\"https://lh3.googleusercontent.com/-uo9b_c78qtc/VP3pY5g3r9I/AAAAAAAACyo/RGUmsCUlAd8/w1341-h894-no/DSC_0312.JPG\"> <img alt=\"\"\n                src=\"https://lh3.googleusercontent.com/ipQJBDMq5iOMhUGtdfZZeucE9QwAWPujQigs4O_0Tvc=w338-h225-p-no\">\n            </a>\n        </div>\n\n        <p>From there we started our most highlighted part of trek, the last few kilometers covered with snow which thicked as we approached near <i>Sandakphu</i>.\n        Few shortcuts made it shorter thanks to the guide who helped us to save some energy! We were mesmerised to see snow covered <i>Sandakphu</i>, after parting\n        with our bag and baggages at one of the lodgings arranged beforehand at Rs.165 per bed went outside to enjoy the weather and landscape, clicked many\n        photographs and played with snow !.</p>\n\n        <div class=\"travelImg\">\n            <a href=\"https://lh5.googleusercontent.com/-8Y49mowRXB4/VP3pzHIKqaI/AAAAAAAACyo/WctaWpmKEbQ/w1341-h894-no/DSC_0438.JPG\"> <img alt=\"\"\n                src=\"https://lh3.googleusercontent.com/DZwG9nZY99aVBMmBGQc5PPcb6l-gR3zpBKU_UdTfHug=w338-h225-p-no\">\n            </a>\n        </div>\n        <p>But this didn\'t last long as the weather was detoriating with biting cold with freezing winds making us numb. We rested for an hour and had dinner\n        at 7:00 pm Rs.150 per plate and went to sleep. All lights switch off after 9:00 pm as there is no electricity but a few solar panels which harldy\n        accumulates enough energy due to overcast weather conditions after noon.</p>\n\n        <div class=\"travelImg\">\n            <a href=\"https://lh3.googleusercontent.com/-WDGoF_5cQYY/VP3puH6i3WI/AAAAAAAACyo/V8-WVSQwH_U/w1341-h894-no/DSC_0422.JPG\"> <img alt=\"\"\n                src=\"https://lh3.googleusercontent.com/L2jUJLc7W7Vge-s0Jgst6whJuHvzICwqdJRnxzIFKAQ=w338-h225-p-no\">\n            </a>\n        </div>\n\n        <br />\n        <p class=\"lead\">Day 4: <i>Sandakphu</i> to <i>Srikhola</i></p>\n        <p>To catch the glimpse of kanchenjunga we got up early at 5:00 am and then came the moment were we got to witness sun rising gradually covering\n        kanchenjunga with its golden rays and moon and sun on opposite ends of sky.</p>\n        <div class=\"travelImg\">\n            <a href=\"https://lh3.googleusercontent.com/-ZZmF3wUhXF4/VP3p4xjr0aI/AAAAAAAACyo/wv02ciHuqsc/w1341-h894-no/DSC_0468.JPG\"> <img alt=\"\"\n                src=\"https://lh3.googleusercontent.com/Rr8wvm5k0ptOPzGnUzovcteklCve-vbgduh5NBN2wAQ=w338-h225-p-no\">\n            </a>\n        </div>\n        <div class=\"travelImg\">\n            <a href=\"https://lh5.googleusercontent.com/V8HYGr8d_gN-wrt6PgYYaKxRrIK74X4VEYG9XWIaRGk=w1341-h894-no\"> <img alt=\"\"\n                src=\"https://lh3.googleusercontent.com/V8HYGr8d_gN-wrt6PgYYaKxRrIK74X4VEYG9XWIaRGk=w338-h225-p-no\">\n            </a>\n        </div>\n\n\n        <p>within few moments we also got to savour many of the 8k+ peaks of the Himalayan range - Mt Everest, Makalu, Lhotse, Three Sisters, Kumbhakarna, Mt.\n        Khangchendzonga and Pandim. One can also see the Eastern peaks of Arunachal when the weather is absolutely clear but we couldn\'t. Time stood still\n        and we just watched in awe - the beauty of the Himalayas. </p>\n\n        <p>We had to trek 17 kilometres to <i>Srikhola</i> for our night stay for which we\n        started early after enjoying the beauty of <i>Sandakphu</i>. One must start early to avoid steep slippery trail downhill coz as the snow starts melting it\n        makes the downward trek risky. Initially the snow covered route was tough as the snow was melting but after 4-5 kilometers downhill the snow\n        disappeared. The view on the way was magnificent one can see Khangchendzonga, three sisters etc.</p>\n        <div class=\"travelImg\">\n            <a href=\"https://lh4.googleusercontent.com/-rl8b4To77eU/VP3p_BWZS3I/AAAAAAAACyo/0IzSOAaJOvQ/w1341-h894-no/DSC_0521.JPG\"> <img alt=\"\"\n                src=\"https://lh3.googleusercontent.com/lDakiGU4CrrQOApMqA6CknQg_IcSQGknEpyoNuMToYI=w338-h225-p-no\">\n            </a>\n        </div>\n\n        <p>We had goose bumps passing through the dense Bamboo forest and then through sloping terrain covered with Rodedendron and Magnolia.</p>\n        <div class=\"travelImg\">\n            <a href=\"https://lh4.googleusercontent.com/-F8zPz7trOuc/VP3qGZuBSfI/AAAAAAAACyo/exHhMWTxwCQ/w596-h894-no/DSC_0570.JPG\"> <img alt=\"\"\n                src=\"https://lh3.googleusercontent.com/Cs-WZL-_hNXImX9Nrw6k3hO-6eq_qaYGKwJVczQSUlE=w136-h202-p-no\">\n            </a>\n        </div>\n\n\n        <p>One can rest in between the journey to regain energy. We passed through that part of forest which got burnt in 1980\'s after which our next halt was\n        <i>Gurdum</i> which was at 14 kilometres from <i>Sandakphu</i>. In case of emergency one can hire a pony from <i>Gurdum</i>. A good guide can arrange it over phone if he\n        has good contacts. One of our fellow trekker was unable to continue and he hired a pony. We reached Gordum at around 1:00 pm and had lunch. In my\n        last trek to <i>Sandakphu</i> in 2012 we stayed at <i>Gurdum</i> at Himalayan Sherpa Lodge. But this time we trekked down further to <i>Srikhola</i>, 4 kilometres from\n        Gordum along a rouring stream and reached there by 4:00 pm.</p>\n        <div class=\"travelImg\">\n            <a href=\"https://lh4.googleusercontent.com/-tE-aXtR2i8I/VP3qJ-WK6oI/AAAAAAAACyo/JVOxpS6qD4c/w1341-h894-no/DSC_0576.JPG\"> <img alt=\"\"\n                src=\"https://lh3.googleusercontent.com/ah2EEF7F4tr6HqKDgD5ZYeK6pvcpBZ3TSwPaGW3-_YU=w304-h202-p-no\">\n            </a>\n        </div>\n\n\n        <p>We were so tired that we could hardly take another step and each and every muscle in our body was aching. We stayed at the Red Panda hotel on the\n        bank of Sirikhola (Khola - river). After taking shower with warm water we seemed to regain some strength to hang around at the river bank. We had\n        egg curry in dinner and went to sleep as the next day we had booked a jeep from <i>Rimbik</i> which was 4 kilometres trek from <i>Srikhola</i>. One can also get a\n        jeep to <i>New Jalpaiguri</i> from <i>Srikhola</i> but there is only one that leaves <i>Srikhola</i> at 6:00 am in the morning.</p>\n\n\n\n        <p class=\"lead\">Day 5: <i>Srikhola</i> to <i>Rimbik</i> to <i>New Jalpaiguri</i></p>\n        <p>After having breakfast we started at 9:00 am reluctantly as we were groaning in pain but as soon as we started we got used to it. The trek to <i>Rimbik</i>\n        was easy as it was almost normal road. We were very near to Sikkim border one can see sikkim on the other side of the hill. A kilometer from\n        <i>Srikhola</i> the two streams (<i>Srikhola</i> and river Rammam) meets. The river Rammam acts as the border between Sikkim and West Bengal.</p>\n        <div class=\"travelImg\">\n            <a href=\"https://lh6.googleusercontent.com/-hFejM_j-wWU/VP3qNUKgDEI/AAAAAAAACyo/gP2F0l5ygtQ/w1341-h894-no/DSC_0603.JPG\"> <img alt=\"\"\n                src=\"https://lh3.googleusercontent.com/HbwPGq3HmbQerJmy_k1kQTLrvk3PKANJKGnDa0AudPY=w338-h225-p-no\">\n            </a>\n        </div>\n\n        <p>We reached <i>Rimbik</i> by 11:00 am and boarded the jeep to Sukhiyapokhri which was booked earlier by our friend cum guide Gopal, for Rs.160 per person.\n        If anyone is looking for a guide in this route the Mr. Gopal Chettri is definitely recommended and I have shared his contact details below as well.\n        On the way we got down at Dhotre the Jeep stopped for lunch, but I went it straight to Dhotre View point to get a glimpse along with my D3300.We\n        reached Sukhiapokhri at 2:30 pm from where we got into other jeep to <i>New Jalpaiguri</i> for Rs.140 per person. We reached <i>New Jalpaiguri</i> by 5:00 pm as\n        it was all foggy there. We had dinner at <i>New Jalpaiguri</i> railway station and waited for our train, Padatik Express which was scheduled at 9:00 pm.</p>\n\n\n\n\n        <p class=\"lead\">Essentials for this Trip</p>\n        <ul>\n            <li>Trekking/ Walking Shoe</li>\n            <li>Cotton Socks</li>\n            <li>Gluffs</li>\n            <li>Candles</li>\n            <li>Sandals or Floaters</li>\n            <li>Water Bottle</li>\n            <li>Earthen/Natural colored clothes – Jeans is absolutely not recommended</li>\n            <li>Warm Clothings (It’s expected to be quite cold at Mul Pokhari)</li>\n            <li>Sun Cap</li>\n            <li>Rain-coat</li>\n            <li>Personal Medicines, Band Aid, Crepe Bandage, etc.</li>\n            <li>Toilet Paper</li>\n            <li>Soap/ Soap Paper/ Hand Sanitizer</li>\n            <li>Torch Light</li>\n            <li>Spare Newspaper</li>\n            <li>Spare Plastic Carry Bags</li>\n            <li>Photo Id Proof (Both Original and Photocopy)</li>\n            <li>Camera</li>\n        </ul>\n\n        <p>Note: All photographs in this site are either taken by me or by my friends.</p><br /> <br />\n\n        <p class=\"lead\">Contact Details of our friend and Guide - Mr. Gopal Chettri</p>\n        <div class=\"travelImg\">\n            <a href=\"https://www.facebook.com/gopal.chettri1\"> <img alt=\"Gopal Chettri\"\n                src=\"https://lh3.googleusercontent.com/ogV4cblsA6XGeSdWxioZmbRdClfC5cb-besyDegwu2w=w311-h207-p-no\"> <br />Gopal Chettri\n            </a> <br /> Ph: +91 9434309606\n        </div>\n                <hr>','https://lh5.googleusercontent.com/-8Y49mowRXB4/VP3pzHIKqaI/AAAAAAAACyo/WctaWpmKEbQ/w1341-h894-no/DSC_0438.JPG','2015-03-06 01:05:01','Pratik Sengupta','Sandakphu Revisited','2015-03-06 01:05:01','Pratik Sengupta','Breakfree into natures lap @Sandakphu','Sandakphu the highest peak of west Bengal is situated at the edge of the Singalila National Park in Darjeeling district on the West Bengal-Nepal border, and is the highest point of the Singalila Ridge. Kangchenjunga, Lhotse and Makalu can be seen from its'),(2,NULL,'','1970-01-01 00:00:01','Pratik Sengupta','Spring Complete Annotation Based Web Application',NULL,NULL,'','Here is how we can configure a spring based Java Web Project without any xml configurations (even skipping the web.xml !). This is made pretty easy by the org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer class of spring-webmvc.'),(7,'tttttttttttj <span style=\"font-weight: bold;\">jjjjjjj</span><div><img src=\"https://lh3.googleusercontent.com/arJ5gj8p--jVnxH1BPvUgkHDqoElWkFUkmLpxu6Q88S7cJM0AOKaHhEC_yEIGNy5dllii1Q0N70lbrOnMWmKWanngABOF-d5j_wuKiEoTsmxr9M_i6JWb2yjFrmWg1UKIyw98KKgTXES2jOz0GTQ8ZF6BMnyE-nqqD8DufCmji18asPfI8F6wRpsGExWs3x-iSULtQzuJ4nooIWJDqxWIytD3vJ3ZhMt0eESzH7rqg3vdLNJpaRiunmA80ijniL-vN2tyuvSX1Ho_A0U3qzaxf8yv4Yx0bT_Yh9NxMQyEEmA2t1pgck9zZ5RXzdOwO8HbdpWsEpd_8WWJ-3U6pbo66xxulzRJM2kv454H1LzKX4HwWCgP5mwg3J2_mvjL7HXS8q61DFzpAfzyYRgWxig2nQelfXovkdJxYnrl9r2tr7XMqU3Na_dUIr0FcJnyX-ZYw-yWwA2rI9W5pxZ4vWC_pJor5TSKWDTBMygZ-YT0GrmFJL8PnNQ75167JC4YSLsh1MlteTgsO3WRVScaHZlwiBAwS_OdaS2Y5BusHxDHbo=w1493-h995-no\"><span style=\"font-weight: bold;\"><br></span></div>','testlink','2015-11-22 02:01:09','Pratik Sengupta','Test Blog','2015-11-22 02:01:09','Pratik Sengupta',NULL,NULL),(8,'tttttttttttj <span style=\"font-weight: bold;\">jjjjjjj</span><div><img src=\"https://lh3.googleusercontent.com/arJ5gj8p--jVnxH1BPvUgkHDqoElWkFUkmLpxu6Q88S7cJM0AOKaHhEC_yEIGNy5dllii1Q0N70lbrOnMWmKWanngABOF-d5j_wuKiEoTsmxr9M_i6JWb2yjFrmWg1UKIyw98KKgTXES2jOz0GTQ8ZF6BMnyE-nqqD8DufCmji18asPfI8F6wRpsGExWs3x-iSULtQzuJ4nooIWJDqxWIytD3vJ3ZhMt0eESzH7rqg3vdLNJpaRiunmA80ijniL-vN2tyuvSX1Ho_A0U3qzaxf8yv4Yx0bT_Yh9NxMQyEEmA2t1pgck9zZ5RXzdOwO8HbdpWsEpd_8WWJ-3U6pbo66xxulzRJM2kv454H1LzKX4HwWCgP5mwg3J2_mvjL7HXS8q61DFzpAfzyYRgWxig2nQelfXovkdJxYnrl9r2tr7XMqU3Na_dUIr0FcJnyX-ZYw-yWwA2rI9W5pxZ4vWC_pJor5TSKWDTBMygZ-YT0GrmFJL8PnNQ75167JC4YSLsh1MlteTgsO3WRVScaHZlwiBAwS_OdaS2Y5BusHxDHbo=w1493-h995-no\"><span style=\"font-weight: bold;\"><br></span></div>','testlink','2015-11-22 02:01:28','Pratik Sengupta','Test Blog','2015-11-22 02:01:28','Pratik Sengupta',NULL,NULL),(9,'tyutyu','ytutyu','2015-11-22 02:13:33','Pratik Sengupta','tytu','2015-11-22 02:13:33','Pratik Sengupta',NULL,NULL),(10,'fghfgh','fhfgh','2015-11-22 02:13:45','Pratik Sengupta','fghfgh','2015-11-22 02:13:45','Pratik Sengupta',NULL,NULL);
/*!40000 ALTER TABLE `blog_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  UNIQUE KEY `UK_fn4pldu982p9u158rpk6nho5k` (`permission_id`),
  CONSTRAINT `FK_fn4pldu982p9u158rpk6nho5k` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `FK_j89g87bvih4d6jbxjcssrybks` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `app_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'1','admin'),(2,'1','user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_code` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `credential_expired` bit(1) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `expipred` bit(1) DEFAULT NULL,
  `locked` bit(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'1','admin','admin','password123','admin','\0','','\0','\0'),(2,'1','user','user','password123','user','\0','','\0','\0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_attr`
--

DROP TABLE IF EXISTS `user_attr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_attr` (
  `attr_id` bigint(20) NOT NULL,
  `attr_name` varchar(255) DEFAULT NULL,
  `attr_value` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`attr_id`),
  KEY `FK_ph082w4kqx270dge3o3ggvao6` (`user_id`),
  CONSTRAINT `FK_ph082w4kqx270dge3o3ggvao6` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_attr`
--

LOCK TABLES `user_attr` WRITE;
/*!40000 ALTER TABLE `user_attr` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_attr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_5q4rc4fh1on6567qk69uesvyf` (`role_id`),
  CONSTRAINT `FK_5q4rc4fh1on6567qk69uesvyf` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `FK_g1uebn6mqk9qiaw45vnacmyo2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-05 18:50:07
