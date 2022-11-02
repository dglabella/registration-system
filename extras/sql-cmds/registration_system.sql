-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Nov 02, 2022 at 08:38 PM
-- Server version: 8.0.18
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `registration_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `accesses`
--

DROP TABLE IF EXISTS `accesses`;
CREATE TABLE IF NOT EXISTS `accesses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `active` bit(1) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `accesses`
--

INSERT INTO `accesses` (`id`, `name`, `active`, `description`) VALUES
(1, 'RECTORADO', b'1', 'PUERTA CAJERO'),
(2, 'BLOQUE 1', b'1', 'ANFI 1'),
(3, 'BLOQUE 2', b'1', 'ANFI 2'),
(4, 'EJPP', b'1', 'MITRE Y LAVALLE'),
(5, 'COMEDOR', b'1', 'PUERTA INGRESO');

-- --------------------------------------------------------

--
-- Table structure for table `credentials`
--

DROP TABLE IF EXISTS `credentials`;
CREATE TABLE IF NOT EXISTS `credentials` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `data` varchar(255) NOT NULL,
  `img` longblob NOT NULL,
  `person_fk` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1j8wje6aitf1udnkjwno1t8ci` (`person_fk`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `credentials`
--

INSERT INTO `credentials` (`id`, `active`, `data`, `img`, `person_fk`) VALUES
(1, b'1', 'L9v7aocVk1hq034ntIJ7lPLwIU3/lFpk8cjE1x9bDD+sPxWrJTwbTQ==', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001b849444154785eedd1416ec3300c4451dd44f7bf950e5220d57c522e91451154dc75e8c4a6868f5e24e3f5797d8df7e49732ae655ccbb896712de35ac6b5fe0b5e839aaf35e338cff7991877e03093a97a3553e99918f7e0fde36ba8874edcf4673c13e346bc3207ebf3f31ae3469cdda0d9df7881711fce15c5689eb973eec6d77850e0b72b27c61db8d6d2ce4b6cbe4d8c6bfd05f3d3cfbc8b6dae2ded7019376109ed10f354b05b6da5366ec08af60a4b0f62edbcc2f81e3fb1064b3b6ce9a94968e37baca1b8a6a7428947667c8b73b0d01092c94d11daf816af7978369ad2cb238d7bf00ac850739d23518037bec5b110116aa8e515f9316ec188a93f24ce2f5a21d2888dafb1026a8268904b8d5ae306bc00318d96c7e41c13e316bc24c7191caa53ae1af7e054e9e3167bbb6766dc81750d621e61950228e37b2c12f91a12e7a3530e8c3b700ce8d963e7f99be0c6b7f8d41ee30123b64928e35bcc8f1f53201bc04516def81a73daf7bd15b7e8472e808c1b708085ca15ce2af5c6adf8809cb3c9ccb8152b8d56300242e31e9c2b674127164b607c8f071558536d67b869bccbf81a7f56c6b58c6b19d732ae655ccbb89671adaf6ff55a231c7a7e76440000000049454e44ae426082, 1),
(2, b'1', 'NP0KligqhXwyT+IkYcMpmuDuq9KoZ5/yg47SCmdqEArA2Si9Qd7MVA==', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001a949444154785eedd1514ec5300c44d1ec84fdef2a0b7952c95c3bad01092162be1897b6af9e33fda0e3faf9bcc6e7cd37635cc7b88e711de33ac6758cebfc173c07f3b67e3de7d4e2498c1b300f02b74d7d27c62d3882ca87b47677c5b80b8ba1745b9b9218ff110e61dc8bf79568c8c7f924c60d78302bfa726462dc81f7ac7f3e5fe6b61f52e33abfc1531f24fff9a1d969994fc63d3876934ef4a846c04b8ccfb19e6594a6258ef718f7e1cc3282d30818b1f1295e0f13252db099caf91ee3167c37a29017f66a1937e1c73dcd88e21b1937e0bb80be148f4477ddf81ccf88c228dc842e2f326ec017947ceaae9883ad71170e045f17458929f012e30e0c8b84d61e8a698d8fb1b81815cefd021aa98dcf71e8c4dcf8532da47103564c8a5798776d3578e3531c60dde41f2234f3346ec04b08e84a4f25513ab1326ec07b64c3e40f8a7a32eec05a68377113c49adf748d3b300f5abfa9c85d4bedf366dc82f363e842ac02a726ac711bbea4ae24897662dc8775bde87072b0376ec2bbc27e75541a718bba7107d67e48ea588f91aacb3a9e8d4ff1cfc6b88e711de33ac6758ceb18d731aef37a07dbd76bc5690c91510000000049454e44ae426082, 2),
(3, b'1', 'XcL908/BQMRlanPYGzjVCaUt4e2YkFWP1+aMznAgI74=', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab70000020049444154785eed98316ec3400c0419a448e927e8297e9af4343dc54f50a9c2c885bba464c2098234be4bb15bd80772ae5a9047cadadf75b7e7c82f125c25b84a7095e02ac15582ab045725bc5968f2c85bc361fbf0b0cd6b2666c1fd61a45a5b9d71b82dd79b253c312178087c874d8f54c0bc6ef62678281c29b3a9edef82ff091c4c94d567b92e7804cc3fa4de9d8110e17548f00838009b363c40342e0f9910dc1f7e68f78cffdd2e9e7ace09ae7a391c0ed22f2f2baf2fbbec1633db6567447077b8c52e73cdc3ecd7d9eba0a82fc1fd61f86547af73ddf800b9838d4f524082bbc22773c12f1e20a4f0ee20227808dca29a56cfa7951ca4594d341707c1bd61ae305e4d86a586c27508db4d46047785778fb685c9d407e3a8af5382a707db01a630984567f3518d87054f52cbfa12dc1bc60ae30e9e30e5f1db7110dc1bce4f3157ac30ee1707e996dd2fbea109ee0fb3764239b3611840a165d3133c004e9b368e6a337a1d0bed7a743fc123e060e817eacbf89d1ffaa1ac04f7804f6d313ffb2d76bf393d0d09ee0a7b849a8ec361dc8a88e03170bc322bb67e8e6ad3c6b29ae9e9b75e27b80f7cf8c5037a1dea2bacf43c6e091e06a3880c2f1135d3dcb052f050d8cbca7c7ece4d132942827bc3fc3be123b2f88d1f7a9de02e3082c6a900a9a5ec3239bc09ee0eff4d82ab045709ae125c25b84a7095e0aa17c25f32b9f2d42f2ab89b0000000049454e44ae426082, 3),
(4, b'1', 'ukzlsDO/e0PBRQPpTrM0m8ck+R9YNAj+z9Qc9RNCdre1rg27AC/Ntg==', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001af49444154785eedd44b4e04310c04d0dca4ef7fab1c0429a43e99b1582044cc8a72673289fd8a058d18ebe7f531be76bea9e05ac1b5826b05d70aae155cebbfe039580fbeb13df3595e9e04b7605eb8730ac4569d0437e06d9eddda87470e0d5f3909eec40b723aa715fc3798073cea07b762ef9a22c4f361c14d78b020bf3e9e0477e05373e8bde035e0b5089d0aaed3dfe0ddc5825c7a0dfb4e89e0d9836f31afd3018ef6f1dd632cf81e73345e73ce9424b40ebec5d2568741f1e376f03546834d8660f53f865d9e823b303c18d6f408c7a1651d7c8ba9d6e90a0cc7def1e05b8ccea0a67b10d00903fd88e07b3cdd9c5cbe3bb2d809eec068d3dbca687bf3e07b6c2da980b274e8073760084e17131a3ae65bf03d9e84587c3433439699e0063c08cf86efc5d87931c12d98f7893e88b36c4cf4e4837bb08e747870545a36f81e0f95810264ee5207dfe253730f8ea7d49534f81ea3ff1a89b9337109eec2bcee7de28f5e85833ea7197c8ff1ab3f6d9eb1fc629c0e6ec490984f1a471d08eec21879438b24b8133b02c9a06f8c05b7e1c112d6f7eb7868f03dfe5905d70aae155c2bb85670ade05ac1b53e3e01b471e34d7a7ebe700000000049454e44ae426082, 4),
(5, b'1', 'l/RiBVs4HCBlmWcWqbQHSQJ8OqV4sq2PY3uk/zL6nheG8uWBgdqYfw==', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001b049444154785eedd3516e0331080450dfc4f7bf150789b4650670503eaaa8e6afc366d736bce98fd5f57c5faff5d9f9a5847b09f712ee25dc4bb89770afff826db13636fbbc7ec42626c21398077e0d91847cde73e17b5cb7c09b21b168d44478106345020b76edcf08cf62ecb8c5223c8df36b8b2f42d13c13e109bc58441f4f4e8427f0a9bc97b806e03e14eec3bf6002c3adf82f3f9b819c09cf609c0fc8133bf52f213c8159182cdc489d37dffc0b8ff03d4663451fcbe207b973141ec17eb6f7301674aa213c82b33c03c81922704c1ab5f03db64211a9ede61b56785f63586fc2648886b94a0adf634cd1756e7cea8c4d5ae17b8c09e8e2c0c79e421369ee8547700ed1448e30aadd8bf03df6b377ea170fd2068eb4f000f6239a1b3bf4a160524653f81e63b643023218592688846730bae004803499129ec1d90ecf087edc33293c828f091f533896f010ae3a6a3343865ef4856fb1b751db027016880f32c2139827b6c33c04f989a6f004ce9625e6ca2efbc2c3f88007b8f2380b4f627b2776e4d8159ec219a1c8c373126c080fe0c5e2202285630d2d7c8bbf2be15ec2bd847b09f712ee25dc4bb8d7eb072304eb45dec7c1290000000049454e44ae426082, 5),
(6, b'1', 'RGXJD6i0n+m0Aq/S1JnVu2VSB0g+91sQnhfsJe9v1rwOLSo6UKu1qGk/szcSLUL0', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001aa49444154785eedd241722b211003506ec2fd6fc5415c4590d430ed2c52aed059456d8719a4d7defcdfe6e7f36adf931fc6388f711ee33cc6798cf318e7f92f78344e5f6f3d82ceb03f8d7101e665a0ea78e8c06d37c6355845540d97967ec0b8164f7c45d7c19bf11fe07392ca1b57e2582145870584bb312ec18d23fcfe89c6b802ef41897cecbdb7d638cf6f304b9c7a8d5b6b3ce3078ceff11ca879d28884416e5c8527811e3828f1442a6d7c8d8f67bb99de3abec61558c168fa53a0f0a1c6f798a9f8fe9fcee4fccb1897e095a2e924539a61540a8c6f317bb0a6ea2c9fc6b802ebb6cf11926ebf18176098468203d5eaf70ed6a98d6f312e5180770642f80136c6f7980bb200f18e1dd8ceabf13d66c80c74ea823d2e2834bec7e42bcc5a0f26c64518259b88b1b882b86e6d7c8ba94f8a07e5f32132bec5eb861ec3867f6b9d51dc8deff19e28c38e58c41ac6f8161335d5002064f82036aec11970ab75be22312ec4036201415abe6213deb8120f86baeb8a039d71259ed0790329bfc635385a70a1a3f6ae7101468b1e0e2bd821c40603e302fcd918e731ce639cc7388f711ee33cc6795e5fedf9231cc2d936540000000049454e44ae426082, 6),
(7, b'1', 'RyxncCA1fako5g+VGlpoXT88F87BSsoN7tJvygbj7lIcGflEuoSVUQ==', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001ae49444154785eedd1cb6dc4300c045075a2febb522101369a8f14660f4110f396a117fa701e7d588fd7efeb63bc777ea8e05ac1b5826b05d70aae155cebbfe03558f3b5a6ae131d9c6f12dc81652695cc3cbdaf35f839de7f3e626cb871808d930437e2e5bed52caf096ec43e0d591c827bb147d0963a2f7112dc8291eb13bc3f4e823b70ad8519ee3edc0aaec95f30fffae975926fe79de7e016bc13aac136140631cb543af82966ee11e7f8a93b6c831bf009068730a34fc2dd3ab8012fa2d3d78d77fc46700bc6158a3bbf82f33b16dc81d102dd68624272f96a1bfc18bb5146ce4f9a28b8013bd85e0f0e6872846f0a6ec03b1c04ec7a1c3d34f49ae006ccdb6eee5488dde9115d831f63c44b6d6ffa404b43c14d580204cb8d35c0c9e02e8c80e7e35df70dc10d581966c8267b9cf735b805a3c789bd7c370c780a7e8e994f3f763a6924b8039f5a5630aca90b9ac1cfb1d3c97d6a068b3ed2fe3ac13d9837b4afc57561f72db805a3c5553927ee47f2cb821bf10118c0c21fcec18d783f2f4d3046a2b1e01e7c4694dd112dc15d78b0841d7367d7ef0a7e8c7f57c1b5826b05d70aae155c2bb85670ad8f4f6f4143ed332622030000000049454e44ae426082, 7),
(8, b'1', 'd8vRxfyUD/QpdsOgO0k7cmmQQY6W3cGTJKDz0bWVIi9Voz1C43FMsZyf/WWrdlhY', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001b949444154785eedd3516ec3300c0350dfc4f7bf950f522033492951f73114b3f6352a6d6c8b4ffd98b1717d5eaff1bdf34319d732ae655ccbb896712de35aff05afc19a58e79abb31f5dc89710be681ef055d5e4f62dc80f71f1f01d232c38612e34e2cc4c66e6963fc2778683f063bc6bd38de48f304fd24c61d78b0262fe1fd89c4b803672d5c83c2a5d19a1ad7f4371809c28b1f6dd166431be306fcf4f0199c08a81391f13126e5cd04174423fe198c5b305a1289a2c97105c6c7186700a13c2e34f023ea1a1fe31de3c544191be8c49471075e38830d2d1a602080323ec6771753843c63c71f60199f63643bda09f2a9636e396ddc816364730c4c6e2007a68dbb308ef145ae8d06b5316ec1d73eaa9349b461397d199f63097411e67633fc847123be27e8982dae1aa3323ec7f7834be1983e5af82bc60d38af60628d8bc0a066898ccf7186b17b3aace0c6a7388bcd15e17e61cd71e3738c067b6beeedd41b5d6c356adc81795cc413b37841734d6e7c8ecb5d5cb8a258eebb326ec55811738a448e65dc8523e49723794062dc806384413eec616460c8b8010f16fafa7228a5a8f139feac8c6b19d732ae655ccbb896712de35aaf2fe24042fc6c706cc70000000049454e44ae426082, 8),
(9, b'1', '5XFlX8KLTU2zOj0i32vMxkA4xRI1SJsuATA2rUxfGMc9dcAOAUhbQw==', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001b849444154785eedd14172c3300c0340fd44ffff151f92195700285be9a19329d953413b09452c7d88c7f579bdc6f7c90f657c96f159c667199f657c96f159ff05c760cdd5e91333d6697d9ec4b801f300a1b124d04e8c7b3058ec68b76aee15e32e7ce11ef8811bf0c67f809f00dffa1877e25ce175c58a07477762dc82072bf1db95897107de8550826fe62d337eafdf6012c5d2eb801eb3810718f7604d29994aac5f0d898ccb181c4982e4db055ae30eaca92436280911646a5cc638434ecc039e13f473bf13e33aa6c91b4b5ce11a0fd2c6551c1c289a52924008a98ccb38fff60cc8b88a6fad1a3760e827c6ad05303c488f322e6372cd826866c3281be32ad6917e7541cccd957068dc82e1d68043b63ce5d65dc655ace97a21d0f4b99c5f5a31ae628a0def552c80891ad7b1fe787005eab3e365dc812f659a09aced7cc2fd08e32ae621c7b9c1f1d865dc829f74060335ecd7b266c6b38af3df671ac44cf98af8ae58c665cc635062716f619e3fc62d38203046cb9b87a132eec5a1290e1866a2a1711fa6e0ad8b7b991877e0bd22f3fcded2b8030f16713acc70c083921b57f167657c96f159c667199f657c96f159c667bdbe009073132c697701b10000000049454e44ae426082, 10),
(10, b'1', 'Y+yQOncCLD3gBwgjbUyvjnnBxQ9vvwJTsyIzxqOwoorRhIrgmcJ9hg==', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001a749444154785eedd1418e03210c44516ec2fd6fd50789d4437d9bb495c5281abc9b32098ded67368cfbfb788dcfca2f615cc3b886710de31ac6358c6bfc177c0d62ea30f9b36ac7b8039364778d4c7691d2316ec0f112b73ecaa49572e268dc88417740e4738d712fd669b02922376ec3b95ffb4d02958e71077e9ee07365c7b803bf23df85f1b91f6387716dfe0503d4bf7898744163d8b803d39203906cc229c68c4fb1e018f126a1d6d3ecc525c63d1892960c13349ac6e75830fa9b3199f3548d1b30b9389d545101638d8fb1b8fa94171b21a3aa3b8c1bf1dae4176450536c1a35eec16a2fb85752be1a35eec2aabff7a4927936eec14b09464b3db12c326ddc80956cab1f3379d8d3c6e718afe2a02d898f3c668ccf3152554e8c313323851a37e188e5838aa40e6bdc83279ffc511db2d1326ec19203af9d544099c2b807ef40300b9bca9544d9f814d38ae6eaad99f77e690de3264cf63494e7a02ed225c62d384bf2546486aad48d9b719c324899d2d1b811ebf3108c7127ce11c9249399750b55e316cc13445923f1107a216c6ae353fc5d18d730ae615cc3b886710de31ac6355e3f64a88ba52c9861090000000049454e44ae426082, 11),
(11, b'1', 'tkvVxHwdHf40dGs1qF9yeAFhMnxNJH5CGhMnUuYjYDBex9MrHFKQ6hxgOUr/IBFukpJgXsT6lZHepr2U/TPmRQ==', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000002bc49444154785eed9a3172eb300c44e171a13247f0517434f1683a8a8ee0328547f8d805ed606cd1cea4fac56e6146c4a30a1820403ae6bfd7cd9e67de487095e02ac15582ab045709ae125cf517f8dba0730c73f3db74bdaccbc9fd1a4f3b2d49093e82bfe26327eca778da6cc1e0def633ec4df018decc020605cf5e56e39af07377bee00fb021366f936fe1ee8854cb49c19f61fefd0858f7b77e165ccd3120361d0e76ec968061173c860d4276d3b375a02529c107f05dd78b830a2b93dcd7a55a0557ebc3cf4119a2715de0ee08cad3cd3089bee6e54b115c6064b7b3983036630d3746b8db10a98287308212fd73b43066e1d939975a6faa09093e8271ca40efe2f46c1c3ddc11b001e79a45f0084650da6ea01a607add99ebd09c6f147c0c7b1cd71c8d7318f90407cf2d4e2068aa058f6050a829884dcfad900e86a64e083e84e9cbeed93cf02236c3dd8d2f7aa9dd827f6014e0ac2939445fd3d7f4fd51f00886c9b37fe653243903964f51a3058fe14b1c7891c82b9605dce20317088e0a3313127c04d3a5f70b5176320d3be29614d6081ec028c7515338600d729d5e6776fb53880a2e307cb9388fb89e1b234c31a0c2c0f98247302ea39cf3b3e3b041d384d8c495c1f35620f8194eaaed2826abb1afc9b48ef646f01b183ffe4ccc670e79865b8d9dcc4c48f031ecbc860ff5eb3c76d358d3735df00076ccd3a516423986096ff0dc2d058fe1c8605e22f3f0c67f15e0b9d7b1d45e0a90e0079cfd73446334ce79b182d8849fbb048fe02e9616f4827d9b6431c1d245f008fea633cfbc5976f62e8d918ae20ce7e7bb041fc171e6c8dfbb1fe67b85e18b16c1637833e433e6f3e8d1ee03341312fc06ce3b29b630fce5d1d112665516fc09b654ac6137ed598e6742828f61cf721ce5a39763e32541ef0c17c14318b69ecf84fb8048e577207804ff4e82ab045709ae125c25b84a7095e0aaff05fe07ad784f29ce8907a60000000049454e44ae426082, 12),
(12, b'1', 'H72TINk4gkSm3+1kug67yH/JCvVe3foPRXAFA6DJKM1Cdh7pp3Qukg==', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001af49444154785eedd1416ec3300c4451dd44f7bf950e12a0d5fca161b58b22a8d95587b12d8a7cd4221a1fefc76b7caffc10c167049f117c46f019c167049ff15ff01ac4d4aa4fbdf3ee04b760366bee5569254eef7ef073cc2d5cf42a9c9de04ebcd36df666ef94cce0bfc04bf0c6da06b7e263a4c6347577825bf0208cbefeaa13dc81af58aafa063e804e2b82cfee6f30e52d795898f19dd009eec0370791087b4e95e0263cf9ff69f37a4e139325b801ab595d53196f9d11c14ff1d6130823df3f67f50d6ec0d5574653ab96e56115825bf0d683a24678613ac14f7007665341f3b0ca827bb0caaeea7eae9a87dc096ec1264b1d99cad8a91bdc83e71a5313c34be58339e9e046ac1f728aec975d701f46ab812aa0ad31e5e00e3ccaae12be213d1c14dc82b1e268dadab8e073821bb0074ca053dbeb1892e0064c89f695e9d184828b0a6ec0572c770a5b710611fc14fbcfdf55248930a30ce0831f633634504ce90c417d88e0e778df00d45d1e5eeeca670537e2a197bc46ab17dc890bb055187344700bae91e2352cae0413dc800721ec8592e7a8e2839fe2f722f88ce03382cf083e23f88ce03382cf787d025039fc26cdfed1fa0000000049454e44ae426082, 13),
(13, b'1', 'wuhrdKuh7lfF8dgthHa/65cLpTWLN7r5dJPJtm1EtGN2RxsgM6UXLc7O/r6vBpPp', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001ba49444154785eedd34b6e0431080450dfc4f7bf950f32524255411a2552348ad9a5e81f86871763cdfa783f5eeb7be59730ee61dcc3b887710fe31ec63dfe0b3e8bb1f1dd674761c77dd6feea188f602e0e5c1a14b0ae8ef10c3e3a0d5475167a9e8ef124263db8a033339ec6518c648132e580f1206e23415040fe748c47f062ecf3f3ca8ef104ae38aae20c98ea302a8c7bf72f387efa8db6eec2bcb3633c81a38dabd205c292923c1ce35b4c79169a09f062a5dec62318a5e2ccb541ed633c83751049ebce3d7440c61398557c6930c169eec0dc78027300a53235208dcd8c0730cb35804f1c0e403c4a18c6b738ab34e0586aa096c64358d5958482df7c190f614811fd1164b989868d2730412504395d0be3010c120f0214edd4f1d16d3c80abae94f4c19c369ec087ab68a85e3cae587fed637c8bc9d516d54bd5dacbf81aebb78fd899622e4b809830bec7159418a083474d5fe37b8c16ba91912d0de485aaf108e6a2e003f8600f86f13d3e446c4717372ecca2216f3c86e929b9c6126b22e3314c87f64201afad05b9f13dce1194b90083d380c2f81a2f06303e9c3ee9f0d266c6b7f8bd30ee61dcc3b887710fe31ec63d8c7bbc3e01ac6f7bb59a1dd4750000000049454e44ae426082, 14),
(14, b'1', 'AqdotX9a8s7MmdCiqQMD4YhdfsKaZXOdt67hXJfueaRE4D5wwk+Y6Q==', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001b249444154785eedd1db6d03310c445175b2fd77a5420c389a4bca4b27401044cc5786de87c439d487773c7f5e8ff1b9f34d19d732ae655ccbb896712de35aff05cf415d6b55efd5b813e306cc663d6f37ae794d3deedcf81c675af9a57b27c69d580ca5d7ea94c4f86fb09a35316ec1fb493428e6eec4b801673cbffef6a07103deb5fe7cbe4ccab7ccf8bd7e83a73e48fef9a1e35ec15e19f7e080cc703ff92e7437373ec5d22b81a7854ccdd033eec0e1a29d7388c044c61df89a81c434c25a8730a5a38c1bb0da9282dc6ce31066d1c6c798c6b62492e0f5336ec2d9d088fabc29217994f129265e036a7329d6822314187760ac5e996bc5462843e373bc5d3294a0be0765dc82d55a06b2e398d2409e67dc8093e9a55d3e3544ced3f8148bd3ae7ee8be90c63d38361211bec0d4cc88838c8ff10e5faf35a7273ea471034eb1cc48a1e5ae38c1f81cef9a23bc72290e811b77603508e3ba27b46667dc82d9a87d6950b166e8e7cbb8054332d7c504436b8531eec320e1a756b07d8271237ed9b97679bdc68d1b708e248d598dc71cb1f1391e9472e930acf9286a1b37e09f95712de35ac6b58c6b19d732ae655cebf101fbed7bb560d152630000000049454e44ae426082, 15),
(15, b'1', 'EZs5mDLhrqMbSVHI7m8Qf4DbyuvT8xGAD05O586v6222TGawWVUXxg==', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001ae49444154785eedd1d16dc4300c03506fe2fdb7d22007a4262927ba16280eb5fe4ae5ea58e2537e3aaecfeb35be4f7e29e35ac6b58c6b19d732ae655cebbfe018ac19930ddfa13313e316cc8631d38973a2df89710f0e8563e68a1abdf036eec51908becf8cdbb01047ebba464f62dc829f95ece3bedda7f1311eac193f9f4c8c3bf053c10ded6db4cbb8667fc18b5c10eb3671c7282f65c9f8146bb8e381ab0cbe8086c4f818833120e7267ae9cbb809d330479290168d711f66a295d8289e11b5f139668f9d0ca4739913e3063cef67a0ca06b78c9bb072da676722e01c57e3062c35e4730577cdb867dc800763246c98f11b7cd4191fe38bec9eae0b7ef8c0c03503e3539cff14ce0302039ee4f919e3733cb580b92e5add332ae3732c9009c314e56e7c8ad18f299b7bf8e15863bc8d9bf05542ae728d036c1a77601044c13f452018728323e353bc8b906bb0587ddec6e718394970ba7644426f59e3732cb30278c5fcc0dc99710f5ed10c3c63dbd5680181712bbe60797296675ae3468c4bfe27b4815f96f139be57b0930bb737eec23ba5dc28e83535eec09f95712de35ac6b58c6b19d732ae655cebf50577ea7bb5ec2f83cd0000000049454e44ae426082, 16),
(16, b'1', '41C+mra59ES5knwvPgmoE4jlmgB7wh+SKpylP2Mb2xCwT+pLxlKv6Q==', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001b949444154785eedd1416ec3300c4451dd44f7bf950e52c0d59f9112218ba2a8b8ebb0b665926fb2a8dbf3fbfa6a9f931f2af8ace0b382cf0a3e2bf8ace0b3fe0b1e4dd53979f4d1e7b0cf6b6f824bb09a81311bcebc37c135787d0953ddd32bbebf5170217e6c1c6c4a05d7e34576431b5c8a8fc811d3f9da075fe3a6d2f2e36f6d822bf0aec1d45f409dd1aee0f7ee6f58e32975f980f98b68135c83d55974f50e617734f81eab9df75676f3273c0faec18f76ac4475b3a75732b8048fae85d8a33d234d9c910a2ec003e6ab1125a1aff3d6c1b7d8cba642ea7d0d140a2ec1509086f36d5f3c940a2ec1afb93f837a4e8eae47700d5e443945518d5b4e157c8bd9511de9d36f1cca065760698da7b0f4481de3e00acc7a7d8c07ae5943f9373c09bec50e3cf23c31efcb89e07bcc1e4ec02fc45e539dc1f7580106a399ab8591df157c8bf56f67c365a0bb51ab09bec7bb06192902fa89d9fb0cbec7b3a524fd8e20a1bfe02aac662ff6d2f3b19be07e8f8558f5c7af76abcc83eb308d5f9184785a075761ce0ea7b7f27b70115e91f9a7a483f35d1999e002ccac4178e5f00fa8772cb800ffae82cf0a3e2bf8ace0b382cf0a3e2bf8acaf6f97113bf5ebebcc0a0000000049454e44ae426082, 17),
(17, b'1', 'jGXCwAUTSQfhrHDHl6WP/pyuH4VNHUJyRoGjEuZuZvHTrznwL0FCOA==', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001b049444154785eedd2516e03210c04506ec2fd6fc541226d99199b58ad5445c57f1d27bb80fd261fa5e3f9bc5ee37be79732ae655ccbb896712de35ac6b5fe0b5e8335b1e235d7feec2e1e4d8c5b300f1293c7b06762dc83751349e32e7888897127069ba248f263dc8e1720f1d91ab7e212e1c34699e06d7c8d076bae9f9f981877e0ac856edcc37b9b655ca77fc1fb4f3f31e697cbf3be134e8c3b701c57be609451c8b8076fb03014199ce75609e31e0c3b9991611e045d068cef71f460f904e38fb08c5b304c4614c82527c63d7868a4e1d2a3fd409709e36b1c929687935084657c8bd181547128cbdb4a6e7c8dc1774b82df08c24eb48d7bf0166be88b983442c84a1bdfe23d41035d702d0f88ba481bdf63f63540f238bd10366ec1688f84e786f0ef9fd2b801d3ec618cf006871d4a187760faece9ad0579fe103ac6b7f8fce9d7d44d90a81d7be3069cb520f8c045221ee37b8c11a6b987c56187a6712316c3e848ed11e6d0b80513a9bbc03335b86860dc87f70399bbcdd431eec4fb8d3eadc052d7b8099fc1e4c2566c248d3bf0600173c12d40ebc0bd7103feac8c6b19d732ae655ccbb896712de35aaf2fdd95fb35450081f50000000049454e44ae426082, 18),
(18, b'1', 'WdAzPVg3jcEqMR3ymhljsgEt2gf0G2PaPGEfLzol9rpEhKrNbwHEmV9BjRTP9D6b', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001b649444154785eedd1516ec3300c0350df44f7bf950f52203549391136602866fd956ae3d8d4533e92717d5eaff133f9a38c6b19d732ae655ccbb89671ad6fc173b062060eb1fe7304d2d81de316ccc3044697235899dc7de373bc4c600d3472c3243bc6bd78c06d9f77e36e7c495c3831366ec6cf0806c839b03bc62d18ad8197ffeb971de30efcd4c4575984f2d2c7d8655c7bffc124632d82401b2360c7f81ca7419393399811e78c3b3003bc7d2129e0628dcf31fac8d516d7012b33e3069c44f9e4e0ce50b4c6c798712a2c9a02dccf30eec1e4038e85761e791937615e7337e65a3516dc1af76050c8e044269c83d5627c8e138a624ba827e060dc83d32bcb21183d612a373ec68c02775e18d30585d0b805df6c39b005f30118e5dfb80193046e18e156c3d2888d1b303c43487d133c813b0d1bf7e01549a3aba3b690a98d4fb1de7fbabb0baf0f84dcf81cef9a684d0df11ccf657c8ef7470194517b7d96408f07e373fcb4d8ce3f73f58c7bf07af5c9b8dda3f55319b761c45c692e6df7b4711f96cb2de7023d5ae3069c2368f0c6692592c61d989f020e7b4d3ed11e303ec59f95712de35ac6b58c6b19d732ae655cebf5069f6e0b3439fb39c80000000049454e44ae426082, 19),
(20, b'1', 'E9UEic8qKwf1nrkoeapgKws4+p+dFEl9U85/13vT4kM8uFX84ZL+Vg==', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001a949444154785eedd1516ec3300c0350dfc4f7bf950f52203349d916fa5114b3f635aa4b228b4ff958daf37dbddafbe44319e732ce659ccb3897712ee35cff058fc6ea78e2d635eb27312ec13cf0ceb4cf47e73925c605789a3e90a305c20e67911857e20772c8ecc6f82f301bb61d73e3521c77a578ce35743b31aec08d05f9fe8bc4b802af1afc1ee743a85b659cd3dfe089f007a90f044479168def31b976f050bb67418c0b3088ece002545be7a605e35b7c029d620f73de39362ec2bc7ab273599d5e627c8f4915ebb166ec18195f63950457493b27da36aec0f3df3e4b893e018496d7c4f81e43c92f0acc29ba615c84993c5c805d37bd439bc6f7584356c4e730afd0c6b7581cb3b83396e48e71090665901c0e8af01ee31a1c083db670de30ac71054602d30810c737d28e710d06e463d6e63933aec03c8efe04e6820eb16d5c81578d6df643af302ec10c18a1ed71c464ae4b1a57609ee77de0abac067bb1cddcf81e6b14b651a989d6b81437348847706c2333aec46c45f6af411b17e15891447e769a3e937101c6608e46ba382527312ec0df95712ee35cc6b98c7319e732ce659cebf50313a763cd1862b9310000000049454e44ae426082, 22),
(21, b'1', 'l1GxB89W1RSL7WqjXDdLtV77QYxU0wCWtZRmsyPXBGnHxLKn4UKdXg==', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001b049444154785eedd24b6e03310c0350dfc4f7bf950f12a0154969a26451041d75556abe169f6611677d7d5e8ff5def9a18c7b19f732ee65dccbb89771afff82cf626d3c71dbd1d9278e2b311ec15c28ddb56cafba1bdfc7d88946634fd8dc57623c8993adc5a5f11fe103a80bcdd7cf180fe03692a1ae4a8c47f0626d846f4726c613b8eaa09bfb20d0cbb8d76f70fcf41b31cfbab60225c62318b93adc13a427fffcea1a4fe0d2e7baa8a073d0780213b0579cef1b5fd1d78c8730f2958fcdb3e6af09e3014c801b306208f6981a8fe0a7423f81684e184fe085da3983534a2b7dc17800a303c02926d5d098f114ce1ffe290e7788514e1bdfc5156304fb02b4d4a01737be8bd93d74212b3c98629f4be3012c19b788e3ca1b6c0e190f60f10c99693c5b9c34be8fd586629f525a93c63398fca09b530cf58d7c371ec0a421192ebc855087afc633b88a1203e934a3cbf83ebe224abeea2d1e3a8c473017d15c3c37c65f6e2ce3fb581bc031a4d1c091fb93dc780e63c1182786b2633c89f1dcc5abab85f1087e8e60c1b1782c1643e3099cdd83930f7d807d0d1a0fe0cfcab897712fe35ec6bd8c7b19f732eef5f80669b3cb65f27049e80000000049454e44ae426082, 23),
(23, b'1', 'Vw210lu20b6lPy/fUX55/vGAOhgSjZHM+hb9fXJcMCe30582XS870g==', 0x89504e470d0a1a0a0000000d494844520000015e0000015e0100000000e5504ab7000001b149444154785eedd14172c3300c0340fd84ffff951e9219d700288bcda193a9d853c1a632452c7d48c6f579bdc6fbe48732ae655ccbb896712de35ac6b5fe0b9e8315d70c5dd1e1dc89710796414e02be66fb343ec7f7978f180fdc023e7b25c68d78e69c3b5c32fe0b9c1de57d4c7c8c3b71ae604ccd8e939d1b1fe3c12279fbcbc4b803d79ad85967d4c4b826bfc1fcea23cf001cf0fb7732eec1810f628e412f2e7d1b1b1fe3c1424cc8199e7c0b3be326cc8c4fc558649f997107d64c405b9c704462dc816568b515972618ac05e3638c9cffc18b96d06b83c8f81cd32898dcc12730cb55e3169c91022c8c947cd018b7e0fba05a89ba89395e61dc8135b9670ad649b95f607c8e15f1878185c00313de8d3b30260889d65dcf7c81710f563cc9877a42ec7262dc8279a61a58591334ba183760a080ce152de538578dcfb11694af1ddef72f647c8c5791e119fcd796962fe373ac2f9fdf3e12e53c27fe8cbb306fa45008d13d0d9171036616e0cac930cdceb815df09ba678045b6c6ad18c3bbcd5edb4f199f639d78224faab7e815c61d78b08463ddb498ef306ec09f95712de35ac6b58c6b19d732ae655cebf5054132033c1dc5c9a00000000049454e44ae426082, 26);

-- --------------------------------------------------------

--
-- Table structure for table `dependencies`
--

DROP TABLE IF EXISTS `dependencies`;
CREATE TABLE IF NOT EXISTS `dependencies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `name` varchar(60) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o28ll9h3tkop6jb8jgawqd486` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `dependencies`
--

INSERT INTO `dependencies` (`id`, `active`, `name`, `description`) VALUES
(1, b'1', 'RECTORADO', NULL),
(2, b'1', 'FCFMYN', NULL),
(3, b'1', 'FQBYF', NULL),
(4, b'1', 'EJPP', NULL),
(5, b'1', 'COMEDOR', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
CREATE TABLE IF NOT EXISTS `persons` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `dependency_fk` int(11) NOT NULL,
  `dni` varchar(15) NOT NULL,
  `last_name` varchar(60) NOT NULL,
  `name` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t0tma5e5ec4leolu2gaqpc9v7` (`dni`),
  KEY `FK45asdrvmcs4956tf0xjo5dgki` (`dependency_fk`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `persons`
--

INSERT INTO `persons` (`id`, `active`, `dependency_fk`, `dni`, `last_name`, `name`) VALUES
(1, b'1', 1, '27567334', 'TISSERA', 'CRISTIAN'),
(2, b'1', 2, '35069255', 'LABELLA', 'DANILO GUIDO'),
(3, b'1', 2, '42667451', 'PAEZ', 'BRAIAN'),
(4, b'1', 4, '28744889', 'MENDEZ', 'GIMENA'),
(5, b'1', 4, '36124867', 'ROBLEDO', 'ESTEFANIA'),
(6, b'1', 4, '25690128', 'VERGARA', 'JORGE ARMANDO'),
(7, b'1', 4, '30998127', 'VILLEGAS', 'VALERIA'),
(8, b'1', 4, '22998674', 'DI GENARO', 'FELIPA TRANSITO'),
(10, b'1', 4, '26928690', 'LUCERO', 'FATIMA ANA'),
(11, b'1', 4, '36110295', 'HANU', 'VERONICA'),
(12, b'1', 4, '39928647', 'LOPEZ RODRIGUEZ', 'GISELLA MARIA JOSEFA'),
(13, b'1', 4, '33009285', 'LOPEZ', 'MARIA BELEN'),
(14, b'1', 4, '31192700', 'ASTRADA', 'GABRIEL DIEGO'),
(15, b'1', 4, '23078122', 'DALESSANDRI', 'BERTO'),
(16, b'1', 4, '344567812', 'VELAZQUES', 'FERNANDA'),
(17, b'1', 4, '21567822', 'BELTRAN', 'NELIDA'),
(18, b'1', 5, '33445119', 'BERMUDES', 'SANTIAGO'),
(19, b'1', 5, '40967103', 'LUCERO', 'HERNAN RODRIGO'),
(22, b'1', 2, '30129998', 'SCHAUWSN', 'PABLO'),
(23, b'1', 4, '24889716', 'VERSALLEZ', 'RAMON'),
(26, b'1', 4, '33987451', 'CASTILLA', 'NAHUEL');

-- --------------------------------------------------------

--
-- Table structure for table `person_roles`
--

DROP TABLE IF EXISTS `person_roles`;
CREATE TABLE IF NOT EXISTS `person_roles` (
  `person_id` int(11) NOT NULL,
  `roles` int(11) DEFAULT NULL,
  KEY `FK8h79dres20ovl8t688wqnu44y` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `person_roles`
--

INSERT INTO `person_roles` (`person_id`, `roles`) VALUES
(1, 2),
(3, 0),
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(10, 2),
(11, 2),
(12, 2),
(13, 2),
(14, 2),
(15, 2),
(16, 2),
(17, 2),
(18, 1),
(19, 1),
(2, 0),
(2, 2),
(22, 1),
(23, 1),
(26, 1);

-- --------------------------------------------------------

--
-- Table structure for table `registers`
--

DROP TABLE IF EXISTS `registers`;
CREATE TABLE IF NOT EXISTS `registers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `access_fk` int(11) NOT NULL,
  `active` bit(1) NOT NULL,
  `check_in` datetime(6) NOT NULL,
  `check_out` datetime(6) DEFAULT NULL,
  `person_fk` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtmfb2oybsntp68y4j8f4w5jop` (`access_fk`),
  KEY `FKmoi12pdkb8c4vrn2lmvubmbu3` (`person_fk`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `registers`
--

INSERT INTO `registers` (`id`, `access_fk`, `active`, `check_in`, `check_out`, `person_fk`) VALUES
(1, 4, b'1', '2022-11-02 08:33:44.192000', '2022-11-02 12:31:55.195000', 8),
(2, 4, b'1', '2022-11-02 17:19:42.587000', NULL, 8);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(60) NOT NULL,
  `active` bit(1) NOT NULL,
  `dni` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(60) NOT NULL,
  `privileges` int(11) NOT NULL,
  `last_name` varchar(60) NOT NULL,
  `name` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r2bdsy4yqtkpssr475ch00bdr` (`account`),
  UNIQUE KEY `UK_6aphui3g30h49muho4c91n0yl` (`dni`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `weeklies`
--

DROP TABLE IF EXISTS `weeklies`;
CREATE TABLE IF NOT EXISTS `weeklies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `end` datetime(6) DEFAULT NULL,
  `friday` bit(1) NOT NULL,
  `monday` bit(1) NOT NULL,
  `person_fk` int(11) NOT NULL,
  `saturday` bit(1) NOT NULL,
  `start` datetime(6) NOT NULL,
  `sunday` bit(1) NOT NULL,
  `thursday` bit(1) NOT NULL,
  `tuesday` bit(1) NOT NULL,
  `wednesday` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhxs6l1a46snu8i4hcmxjhbsf8` (`person_fk`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `weeklies`
--

INSERT INTO `weeklies` (`id`, `active`, `end`, `friday`, `monday`, `person_fk`, `saturday`, `start`, `sunday`, `thursday`, `tuesday`, `wednesday`) VALUES
(1, b'1', NULL, b'1', b'1', 1, b'0', '2022-11-01 09:03:18.578000', b'0', b'1', b'1', b'1'),
(2, b'1', '2022-11-02 13:44:31.952000', b'1', b'1', 2, b'0', '2022-11-01 09:04:06.993000', b'0', b'1', b'1', b'1'),
(3, b'1', NULL, b'1', b'1', 3, b'0', '2022-11-04 15:25:43.511000', b'0', b'1', b'1', b'0'),
(4, b'1', NULL, b'1', b'1', 4, b'0', '2022-12-01 05:00:00.000000', b'0', b'1', b'1', b'1'),
(5, b'1', NULL, b'1', b'1', 5, b'0', '2022-11-01 09:12:46.681000', b'0', b'1', b'1', b'1'),
(6, b'1', NULL, b'1', b'1', 6, b'0', '2022-11-01 09:13:10.036000', b'0', b'1', b'1', b'1'),
(7, b'1', NULL, b'1', b'1', 7, b'0', '2022-11-01 09:13:29.145000', b'0', b'1', b'1', b'1'),
(8, b'1', NULL, b'1', b'1', 8, b'0', '2022-11-01 09:14:03.222000', b'0', b'1', b'1', b'1'),
(9, b'1', NULL, b'1', b'1', 10, b'0', '2022-11-01 09:14:46.088000', b'0', b'1', b'1', b'1'),
(10, b'1', NULL, b'1', b'1', 11, b'0', '2022-11-01 09:15:36.849000', b'0', b'1', b'1', b'1'),
(11, b'1', NULL, b'1', b'1', 12, b'0', '2022-11-01 09:16:09.545000', b'0', b'1', b'1', b'1'),
(12, b'1', NULL, b'1', b'1', 13, b'0', '2022-11-01 09:16:26.482000', b'0', b'1', b'1', b'1'),
(13, b'1', NULL, b'1', b'1', 14, b'0', '2022-11-01 09:17:16.198000', b'0', b'1', b'1', b'1'),
(14, b'1', NULL, b'1', b'1', 15, b'0', '2022-11-01 09:19:20.311000', b'0', b'1', b'1', b'1'),
(15, b'1', NULL, b'1', b'1', 16, b'0', '2022-11-01 20:30:20.311000', b'0', b'0', b'1', b'1'),
(16, b'1', NULL, b'1', b'1', 17, b'0', '2022-11-02 04:20:29.756000', b'0', b'1', b'1', b'1'),
(22, b'1', NULL, b'1', b'1', 18, b'0', '2022-11-02 08:30:02.182000', b'0', b'1', b'1', b'1'),
(23, b'1', NULL, b'1', b'1', 19, b'0', '2022-11-02 08:44:07.922000', b'0', b'1', b'1', b'1'),
(24, b'1', NULL, b'0', b'1', 2, b'0', '2022-11-02 14:44:07.922000', b'0', b'1', b'1', b'1'),
(25, b'1', NULL, b'1', b'1', 22, b'0', '2022-11-02 16:52:22.708000', b'0', b'1', b'1', b'1'),
(26, b'1', NULL, b'1', b'0', 23, b'0', '2022-11-02 16:54:33.331000', b'0', b'1', b'1', b'1'),
(27, b'1', NULL, b'1', b'0', 26, b'0', '2022-11-03 16:54:33.331000', b'0', b'1', b'1', b'1'),
(28, b'1', NULL, b'1', b'1', 26, b'0', '2022-11-02 17:26:36.305000', b'0', b'1', b'0', b'1');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `credentials`
--
ALTER TABLE `credentials`
  ADD CONSTRAINT `FK1j8wje6aitf1udnkjwno1t8ci` FOREIGN KEY (`person_fk`) REFERENCES `persons` (`id`);

--
-- Constraints for table `persons`
--
ALTER TABLE `persons`
  ADD CONSTRAINT `FK45asdrvmcs4956tf0xjo5dgki` FOREIGN KEY (`dependency_fk`) REFERENCES `dependencies` (`id`);

--
-- Constraints for table `person_roles`
--
ALTER TABLE `person_roles`
  ADD CONSTRAINT `FK8h79dres20ovl8t688wqnu44y` FOREIGN KEY (`person_id`) REFERENCES `persons` (`id`);

--
-- Constraints for table `registers`
--
ALTER TABLE `registers`
  ADD CONSTRAINT `FKmoi12pdkb8c4vrn2lmvubmbu3` FOREIGN KEY (`person_fk`) REFERENCES `persons` (`id`),
  ADD CONSTRAINT `FKtmfb2oybsntp68y4j8f4w5jop` FOREIGN KEY (`access_fk`) REFERENCES `accesses` (`id`);

--
-- Constraints for table `weeklies`
--
ALTER TABLE `weeklies`
  ADD CONSTRAINT `FKhxs6l1a46snu8i4hcmxjhbsf8` FOREIGN KEY (`person_fk`) REFERENCES `persons` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
