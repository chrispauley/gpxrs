-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 15, 2016 at 02:11 AM
-- Server version: 5.5.42
-- PHP Version: 5.6.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: gpxdb
--

-- --------------------------------------------------------

--
-- Table structure for table bounds
--

CREATE TABLE bounds (
  bndid int(11) NOT NULL,
  metid int(11) DEFAULT NULL COMMENT 'Link to metadata table',
  minlat float(10,6) NOT NULL,
  minlon float(10,6) NOT NULL,
  maxlat float(10,6) DEFAULT NULL,
  maxlon float(10,6) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table bounds
--

INSERT INTO bounds (bndid, metid, minlat, minlon, maxlat, maxlon) VALUES
(2, 3, 40.330055, -74.929504, 40.408920, -75.043320),
(3, 4, 40.340919, -74.945808, 40.406307, -75.037827),
(4, 5, 40.346401, -75.033363, 40.346401, -75.033363),
(5, 6, 40.347301, -75.036880, 40.347301, -75.036880),
(6, 7, 40.347801, -75.032265, 40.347801, -75.032265),
(7, 8, 40.351383, -75.010567, 40.351383, -75.010567),
(8, 9, 40.340919, -74.945808, 40.406307, -75.037827),
(9, 10, 40.330055, -74.929504, 40.408920, -75.043320),
(10, 11, 39.530052, -75.833084, 39.658279, -75.562370),
(11, 12, 40.595989, -74.859856, 40.620388, -74.797302),
(15, 16, 40.340919, -74.945808, 40.406307, -75.037827);

-- --------------------------------------------------------

--
-- Table structure for table copyright
--

CREATE TABLE copyright (
  crtid int(11) NOT NULL,
  metid int(11) DEFAULT NULL COMMENT 'Link to metadata table',
  author varchar(60) DEFAULT NULL,
  `year` varchar(4) DEFAULT NULL COMMENT 'gYear',
  license varchar(60) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table copyright
--

INSERT INTO copyright (crtid, metid, author, year, license) VALUES
(1, 4, 'Creative Commons', '2011', 'http://creativecommons.org/licenses/by/3.0/'),
(2, 5, 'Groundspeak, Inc.', '2011', NULL),
(3, 6, 'Groundspeak, Inc.', '2011', NULL),
(4, 7, 'Groundspeak, Inc.', '2011', NULL),
(5, 8, 'Groundspeak, Inc.', '2011', NULL),
(6, 9, 'Creative Commons', '2011', 'http://creativecommons.org/licenses/by/3.0/');

-- --------------------------------------------------------

--
-- Table structure for table email
--

CREATE TABLE email (
  emailid int(11) NOT NULL,
  id varchar(60) DEFAULT NULL COMMENT 'id half of email address (info)',
  domain varchar(60) DEFAULT NULL COMMENT 'Domain half of email address (nhspoi.com)'
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table email
--

INSERT INTO email (emailid, id, domain) VALUES
(2, 'pauleyc', 'nhspoi.com'),
(3, 'nhspoi.com', 'info'),
(4, 'info', 'nhspoi.com'),
(5, 'pauleyc', 'nhspoi.com'),
(6, 'carinci', 'verizon.net'),
(10, 'chris', 'nhspoi.com');

-- --------------------------------------------------------

--
-- Table structure for table extension
--

CREATE TABLE extension (
  extid int(11) NOT NULL,
  nspid int(11) DEFAULT NULL,
  wpeid int(11) DEFAULT NULL,
  exttype varchar(20) DEFAULT NULL COMMENT '[gpx|waypoint|metadata|rte|rtept|trk|trkpt]',
  payload varchar(2000) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table extension
--

INSERT INTO extension (extid, nspid, wpeid, exttype, payload) VALUES
(1, 1, 1, 'waypoint', NULL),
(4, 1, 4, 'waypoint', NULL);

-- --------------------------------------------------------

--
-- Table structure for table extns
--

CREATE TABLE extns (
  nspid int(11) NOT NULL,
  xsdname varchar(200) DEFAULT NULL COMMENT 'example - xmlns:gpx_style',
  xsdvalue varchar(200) DEFAULT NULL COMMENT 'http://www.topografix.com/GPX/1/1',
  xsiloc varchar(200) DEFAULT NULL COMMENT 'http://www.topografix.com/GPX/1/1/gpx.xsd'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table gpx
--

CREATE TABLE gpx (
  gpxid int(11) NOT NULL,
  metid int(11) DEFAULT NULL,
  extid int(11) DEFAULT NULL,
  version varchar(10) DEFAULT NULL,
  creator varchar(120) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table gpx
--

INSERT INTO gpx (gpxid, metid, extid, version, creator) VALUES
(2, 2, NULL, '1.1', 'NHSPOI'),
(3, 3, NULL, '1.1', 'NHSPOI GPXDB'),
(4, 4, NULL, '0.9', 'NHSPOI'),
(5, 5, NULL, '1.1', 'http://www.groundspeak.com'),
(6, 6, NULL, '1.1', 'http://www.groundspeak.com'),
(7, 7, NULL, '1.1', 'http://www.groundspeak.com'),
(8, 8, NULL, '1.1', 'http://www.groundspeak.com'),
(9, 9, NULL, '0.9', 'NHSPOI'),
(10, 10, NULL, '1.1', 'NHSPOI GPXDB'),
(11, 11, NULL, '1.1', 'MapSource 6.14.1'),
(12, 12, NULL, '1.1', 'MapSource 6.5'),
(16, 16, NULL, '1.1', 'NHSPOI');

-- --------------------------------------------------------

--
-- Table structure for table guestbook
--

CREATE TABLE guestbook (
  id int(4) NOT NULL,
  `name` varchar(65) NOT NULL DEFAULT '',
  email varchar(65) NOT NULL DEFAULT '',
  `comment` longtext NOT NULL,
  `datetime` varchar(65) NOT NULL DEFAULT ''
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;

--
-- Dumping data for table guestbook
--

INSERT INTO guestbook (id, name, email, comment, datetime) VALUES
(8, 'Chris Pauley', 'pauleyc@soleburyinternetmarketing.com', 'I''d like to buy t-shirts for my construction crew.', '09-03-22 05:47:34'),
(9, 'Chris Pauley', 'pauleyc@soleburyinternetmarketing.com', 'This is a test of the contact form. It has google analytics script embedded in it. We will get bounce rate data from this. The html is really basic for now.', '09-03-22 06:08:52'),
(10, 'Chuck Wohl', '', 'Don...if it would help with design, about 33 letters can be dropped from the design by using nicknames for the 12 siblings:  Chuck, John, Mary, Trish, Ray, Rob, Mike, Liz, Kath, Rick, Mags and Dave.  \r\nAlso left a phone message earlier this morning that the following can also be dropped if necessary:  7 Brothers  5 Sisters', '09-03-31 07:39:11'),
(12, 'Chris Pauley', 'pauley.chris@gmail.com', 'We''re interested in custom t-shirts. ', '09-06-12 03:57:13'),
(13, 'meshell', 'punkrockmeshell@aol.com', 'it says 119. +  ? for 24 shirts...so that is a design on the front and the logo on the back?? thanx..i just wasnt sure what the special was exactly.', '09-08-20 11:38:42'),
(16, 'Lisa Martini', 'Lisa@bcaf.com', 'Hi Don, I will be emailing you our sword logo and lettering.  I''d like to stop in this afternoon with a sample of what we need. Can we get an order in & delivered in time for Xmas? Thanx, Lisa Martini (Bucks County Academy of Fencing)', '09-11-21 11:00:58'),
(19, 'Aaron', 'info@projectlon.biz', 'Hello.\r\nI want to exchange with you linking to my site\r\nfor good reward\r\n\r\nMy site http://projectlon.biz', '10-01-12 07:55:19'),
(38, 'Chrisp', 'pauleyc@yahoo.com', 'This is a test of my contact form', '10-10-29 07:41:31'),
(39, 'Chrisp', 'pauleyc@yahoo.com', 'This is another test of my contact form', '10-10-29 08:09:45'),
(40, '', '', '', '10-11-11 08:33:29'),
(41, 'Jack Gudin', 'jgudin@comcast.net', 'We would like a quote on the website changes. Asp i would like to get some more changes before christmas. Please contact me asp.', '10-11-26 04:01:41'),
(42, '', '', '', '11-01-11 05:17:55'),
(43, 'Me', 'Pauleyc@yahoo.com', 'Testing detail', '11-03-13 08:13:52'),
(44, '', '', '', '11-04-01 03:02:50'),
(45, 'Google''s Algorithm Changes', '', 'Google is now using business reviews to determine business ranking. People are trashing companies with reviews, Complaint sites and Blogs.\r\n\r\nWe can help you defend your company by posting positive Reviews, Blogs and creating Websites to take over Search Results and control what people see about your company.\r\n\r\n(888)-411-7579\r\n\r\nhttp://www.posting-good-reviews.com/    for Review Posting Service\r\nhttp://www.Defend-Experts.com  for Reviews and Reputation Service\r\n\r\nHow does posting positive reviews help in your businesses Google ranking?\r\n\r\n1. Positive reviews increase your business rank by linking important and relevant websites to your website.\r\n2. A constant stream of positive reviews improves your online reputation.\r\n3. Positive reviews drive traffic to your business.\r\n4. Positive reviews restore a tarnished reputation by pushing down negative reviews and links.\r\n5. Helps protect against competitors or anyone else from attempting to run your ranking.\r\n\r\n(888)-411-7579\r\n\r\nhttp://www.posting-good-reviews.com/    for Review Posting Service\r\nhttp://www.Defend-Experts.com  for Reviews and Reputation Service\r\n\r\nTired of review sites? Hire us to knock them off the front page of Google under your search term.\r\nOur company has been in the business of taking over the first page of google for our clients for 8 years and knocking off complaint sites. We can do the same for your company and review sites.\r\nWe will create special websites and blogs and link them to the 15 thousand websites we already have. These websites will knock the review sites away and replace them with content you control.', '11-04-28 08:46:37'),
(46, 'Dannyw', 'dannywills@gmail.com', 'Hi Andrea,    \r\n    \r\nYou asked me to contact you regarding a solution to your slow computer.    \r\nTake a look at this tool I just found, tought you might be interested, here is the link: http://bit.ly/CleanupWindows     \r\n    \r\nEnjoy', '11-05-09 02:49:27'),
(47, 'paula coll', 'paulacoll@comcast.net', 'Hi,\r\n\r\nCould you please provide a ball park estimate for single color printing on a basic canvas tote bag, maybe 10 x 12 inches or so.', '11-05-17 02:34:12'),
(48, 'Google''s  Algorithm Changes', '', 'Google is now using business reviews to determine business ranking. A search for your business shows at least one negative review.\r\n\r\nWe can help you.\r\n\r\n888-411-7579 http://www.dominatereviews.com/ Review Posting Service\r\n\r\nwww.defend-experts.com  Reputation Repair Services\r\n\r\n\r\nHow does posting positive reviews help in your businesses Google ranking?\r\n\r\n1. Positive reviews increase your business rank by linking important and relevant websites to your website.\r\n2. A constant stream of positive reviews improves your online reputation.\r\n3. Positive reviews drive traffic to your business.\r\n4. Positive reviews restore a tarnished reputation by pushing down negative reviews and links.\r\n5. Helps protect against competitors or anyone else from attempting to run your ranking.\r\n\r\n888-411-7579  http://www.dominatereviews.com/  Review Posting Service\r\n\r\nTired of review sites? Hire us to knock them off the front page of Google under your search term.\r\nOur company has been in the business of taking over the first page of google for our clients for 8 years and knocking off complaint sites. We can do the same for your company and review sites.\r\nWe will create special websites and blogs and link them to the 15 thousand websites we already have.  These websites will knock the review sites away and replace them with content you control.\r\n\r\n888-411-7579 http://www.dominatereviews.com/\r\nwww.defend-experts.com  Reputation Repair Services\r\n\r\n', '11-05-22 07:03:08'),
(49, 'Positive Reviews', '', 'Having too few reviews or want to boost your online presence? \r\n\r\nWe can improve your online reputation by posting positive reviews on major review sites.  People go there when they want to find good, reliable services in which they can trust. \r\n\r\nHaving positive reviews on those sites will drive more people to choose your business and will make your name stand up from all of the other competitors. \r\n\r\nOur services include:\r\n- Improving your reputation by posting good reviews and other links;\r\n- Suppressing negative reviews or other links that might damage your reputation, with positive ones which you can control;\r\n- Increasing your online popularity by creating websites and offering SEO (Search Engine Optimization) services.\r\n\r\nFind out more about how we can help by visiting our website at http://postingonlygoodreviews.com/ \r\n\r\nYou can contact us at makereviews@gmail.com or call us at 512-547-1170.\r\n\r\nThank you for according us your time and understanding\r\n\r\n\r\n', '11-06-01 04:27:50'),
(50, 'Mike Omaha', 'mike.omaha5@scholarships.edu', 'Are 3 Minutes of Your Time Worth 10,000 Dollars for your education? <a href=http://bit.ly/jFDLus>Register</a> here until June 30th - or use the direct link: http://bit.ly/jFDLus', '11-06-17 01:47:08'),
(51, 'Google''s Algorithm Change', '', 'Google is now using business reviews to determine business ranking. People are trashing companies with reviews, Complaint sites and Blogs.\r\n\r\nWe can help you defend your company by posting positive Reviews, Blogs and creating Websites to take over Search Results and control what people see about your company.\r\n\r\n512-275-6123\r\n\r\nhttp://www.posting-good-reviews.com/    for Review Posting Service\r\nhttp://www.Defend-Experts.com  for Reviews and Reputation Service\r\n\r\nHow does posting positive reviews help in your businesses Google ranking?\r\n\r\n1. Positive reviews increase your business rank by linking important and relevant websites to your website.\r\n2. A constant stream of positive reviews improves your online reputation.\r\n3. Positive reviews drive traffic to your business.\r\n4. Positive reviews restore a tarnished reputation by pushing down negative reviews and links.\r\n5. Helps protect against competitors or anyone else from attempting to run your ranking.\r\n\r\n512-275-6123\r\n\r\nhttp://www.posting-good-reviews.com/    for Review Posting Service\r\nhttp://www.Defend-Experts.com  for Reviews and Reputation Service\r\n\r\nTired of review sites? Hire us to knock them off the front page of Google under your search term.\r\nOur company has been in the business of taking over the first page of google for our clients for 8 years and knocking off complaint sites. We can do the same for your company and review sites.\r\nWe will create special websites and blogs and link them to the 15 thousand websites we already have. These websites will knock the review sites away and replace them with content you control.', '11-07-02 06:29:28'),
(52, 'jon bece', 'jon.bece@gmail.com', 'Dear webmaster,\r\n \r\nI have visited your site today. After reviewing your website, I thought you would be a great candidate for exchanging link.\r\n \r\nBy using following details\r\n \r\nTitle: SEO Company\r\nURL: http://www.zoomtraffics.com/\r\nLink Exchange: http://www.zoomtraffics.com/linkbuilding.html\r\nDescription: SEO Company offering SEO Consultancy as Search Engine Optimization Consultant\r\n\r\nIf you have any questions or concerns, please don’t hesitate in contacting me by replying to this mail. I will then get back to you as soon as possible.\r\n \r\nThanks & Regards,\r\n\r\nJon Bece\r\nSEO Expert,\r\nZoomtraffics.com\r\n', '11-07-14 07:12:08'),
(54, 'inaminguilync', 'b.ri.dgwo.o.d.e.ne.w@gmail.com', 'high-definition entertainment from Sony in any room with the BX320 series. Featuring 720p HD resolution, five HD inputs, a USB port for your digital media, and Sony technologies for a great picture no matter the environment, the 32-inch KDL-32BX320 is sure to fit into your home as well as your budget. High-Definition in a Compact Size  \r\n<a href=http://tvlcd32inch.info>Tv lcd 32</a>', '11-08-29 07:40:34'),
(55, 'Kalsecreark', 'supperport@junkmailcars.com', 'The talk within the finest worn cellular phone quest of traffic is built to be neck nowadays fiercely asserted amidst consumers. There are lots of brands from which to opt with a lot of models. Then you necessary capabilities to take into account, and also battery. In the motivation you must select the one that’s upstanding on you, and also period’s your own private meet with of style. The fashionable best three hottest employed cellular phones at one''s disposal would be the BlackBerry Bead, Your Apple IPhone, as luckily as the Motorola talkabout Blade. All three or more of such are imaginary candidates and each has their entirely own durability as well as frail spots. We desire look at each solitary of these respectively so that you can decide repayment for yourself which of such you have the courage of one''s convictions pretend will be best.              \r\n<a href=http://www.usedcellphonesforsales.info/>Used cellphones for sale</a>  \r\n<a href=http://ri.simi.org.br/2011/06/16/caf-primeiro-concurso-internacional-de-projetos-de-desenvolvimento-urbano-e-social-em-assentamentos-informais/comment-page-1/#comment-15117>Adapted to cellphones someone is concerned sale</a>\r\n', '11-10-23 11:44:45'),
(56, '', '', '', '12-03-13 08:50:28'),
(57, 'About Your Business', 'regardingyourbusiness@gmail.com', 'You don''t have a good rating on the high traffic websites!? We can help you!\r\n\r\nWhat Are People Saying About Your Business? We can help you!\r\n\r\nIt is incredibly important to analyze your competition and play on their weaknesses and your strengths. Through the use of press releases, directories, forums, Google and Yahoo Local Business and other social media hubs will place your business on top of the search results and will bring more clients for you.\r\n\r\nDid you know that most people make buying decisions not based on what you tell them or what they hear, but based on reviews from complete strangers?\r\nWe can help you!\r\n\r\nGet a package starting from 29.95$/month\r\n\r\nSee more packages and services at www.buygoodreviews.com\r\n\r\nIf you don’t want to receive any of our offers, please go to http://buygoodreviews.com/unsubscribe.php and submit your website address. We will never send you any of our offers to any email assigned to your domains.', '12-04-16 01:38:52');

-- --------------------------------------------------------

--
-- Table structure for table gx_address
--

CREATE TABLE gx_address (
  aid int(11) NOT NULL,
  wpeid int(11) DEFAULT NULL COMMENT 'Link to gx_wptext table',
  city varchar(80) DEFAULT NULL,
  state varchar(2) DEFAULT NULL,
  country varchar(80) DEFAULT NULL,
  postalcode varchar(80) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table gx_address
--

INSERT INTO gx_address (aid, wpeid, city, state, country, postalcode) VALUES
(1, 1, 'New Hope', 'PA', 'US', NULL),
(2, 2, 'New Hope', 'PA', 'US', NULL),
(3, 3, 'New Hope', 'PA', 'US', NULL),
(4, 4, 'New Hope', 'PA', 'US', NULL),
(5, 5, 'New Hope', 'PA', 'US', NULL);

-- --------------------------------------------------------

--
-- Table structure for table gx_address_street
--

CREATE TABLE gx_address_street (
  asid int(11) NOT NULL,
  aid int(11) DEFAULT NULL COMMENT 'Link to gx_address table',
  streetaddress varchar(200) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table gx_address_street
--

INSERT INTO gx_address_street (asid, aid, streetaddress) VALUES
(1, 1, '123 Bridge Street'),
(2, 2, '123 Bridge Street'),
(3, 3, '123 Bridge Street'),
(4, 4, ' 111 W. Bridge Street'),
(5, 5, '123 Bridge Street');

-- --------------------------------------------------------

--
-- Table structure for table gx_autoroutepoint
--

CREATE TABLE gx_autoroutepoint (
  arpid int(11) NOT NULL,
  rpeid int(11) DEFAULT NULL COMMENT 'Link to gx_rptext table',
  lat float(10,6) NOT NULL,
  lon float(10,6) NOT NULL,
  subclass_id int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table gx_categories
--

CREATE TABLE gx_categories (
  catid int(11) NOT NULL,
  wpeid int(11) DEFAULT NULL COMMENT 'Link to gx_wptext table',
  categories varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table gx_category
--

CREATE TABLE gx_category (
  catid int(11) NOT NULL,
  wpeid int(11) DEFAULT NULL COMMENT 'Link to gx_wptext table',
  category varchar(80) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table gx_category
--

INSERT INTO gx_category (catid, wpeid, category) VALUES
(1, 1, 'favorites'),
(2, 2, 'favorites'),
(3, 3, 'favorites'),
(4, 4, 'inn'),
(5, 5, 'favorites');

-- --------------------------------------------------------

--
-- Table structure for table gx_phonenumber
--

CREATE TABLE gx_phonenumber (
  pnid int(11) NOT NULL,
  wpeid int(11) DEFAULT NULL COMMENT 'Link to gx_wptext table',
  category varchar(80) DEFAULT NULL,
  content varchar(80) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table gx_phonenumber
--

INSERT INTO gx_phonenumber (pnid, wpeid, category, content) VALUES
(1, 1, 'phone1', '215-862-0000'),
(2, 1, 'phone2', '215-862-0001'),
(3, 1, 'fax', '215-862-0003'),
(4, 2, 'phone1', '215-862-0000'),
(5, 2, 'phone2', '215-862-0001'),
(6, 2, 'fax', '215-862-0003'),
(7, 3, 'phone1', '215-862-0000'),
(8, 3, 'phone2', '215-862-0001'),
(9, 3, 'fax', '215-862-0003'),
(10, 4, 'phone1', '215-862-2570'),
(11, 4, 'phone2', '215-862-xxxx'),
(12, 4, 'fax', '215-862-yyyy'),
(13, 5, 'phone1', '215-862-0000'),
(14, 5, 'phone2', '215-862-0001'),
(15, 5, 'fax', '215-862-0003');

-- --------------------------------------------------------

--
-- Table structure for table gx_rptext
--

CREATE TABLE gx_rptext (
  rpeid int(11) NOT NULL,
  extid int(11) DEFAULT NULL COMMENT 'Link to extensions table. exttype=rptpt',
  temperature float(10,6) DEFAULT NULL COMMENT 'measured in degrees Celsius',
  depth float(10,6) DEFAULT NULL COMMENT 'in meters'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table gx_rteext
--

CREATE TABLE gx_rteext (
  reid int(11) NOT NULL,
  extid int(11) DEFAULT NULL COMMENT 'Link to extensions table. exttype=rte',
  isautonamed tinyint(1) DEFAULT NULL,
  displaycolor varchar(20) DEFAULT NULL COMMENT 'enumerated. see gpxx.xsd'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table gx_tptext
--

CREATE TABLE gx_tptext (
  tpeid int(11) NOT NULL,
  extid int(11) DEFAULT NULL COMMENT 'Link to extensions table. exttype=trkpt',
  temperature float(10,6) DEFAULT NULL COMMENT 'measured in degrees Celsius',
  depth float(10,6) DEFAULT NULL COMMENT 'in meters'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table gx_trkext
--

CREATE TABLE gx_trkext (
  tkeid int(11) NOT NULL,
  extid int(11) DEFAULT NULL COMMENT 'Link to extensions table. exttype=trk',
  displaycolor varchar(20) DEFAULT NULL COMMENT 'enumerated. see gpxx.xsd'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table gx_wptext
--

CREATE TABLE gx_wptext (
  wpeid int(11) NOT NULL,
  extid int(11) DEFAULT NULL COMMENT 'Link to extensions table. exttype=waypoint',
  proximity float(10,6) DEFAULT NULL COMMENT 'in meters',
  degreescelcius float(10,6) DEFAULT NULL COMMENT 'contains a temperature value measured in degrees Celsius',
  depth float(10,6) DEFAULT NULL COMMENT 'in meters',
  displaymode varchar(80) DEFAULT NULL COMMENT '{SymbolOnly|SymbolAndName|SymbolAndDescription}',
  aid int(11) DEFAULT NULL COMMENT 'Link to gx_address table'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table gx_wptext
--

INSERT INTO gx_wptext (wpeid, extid, proximity, degreescelcius, depth, displaymode, aid) VALUES
(1, 1, 5.000000, NULL, 0.000000, '', 1),
(2, 2, 5.000000, NULL, 0.000000, '', 2),
(3, 3, 5.000000, NULL, 0.000000, '', 3),
(4, 4, 5.000000, NULL, 0.000000, '', 4),
(5, 5, 5.000000, NULL, 0.000000, '', 5);

-- --------------------------------------------------------

--
-- Table structure for table keywords
--

CREATE TABLE keywords (
  kwid int(11) NOT NULL,
  metid int(11) DEFAULT NULL COMMENT 'Link to metadata table',
  keyword varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table link
--

CREATE TABLE link (
  linkid int(11) NOT NULL,
  metid int(11) DEFAULT NULL,
  perid int(11) DEFAULT NULL,
  wptid int(11) DEFAULT NULL,
  rteid int(11) DEFAULT NULL,
  rptid int(11) DEFAULT NULL,
  trkid int(11) DEFAULT NULL,
  tptid int(11) DEFAULT NULL,
  href varchar(200) DEFAULT NULL COMMENT 'URL of hyperlink.',
  `text` varchar(100) DEFAULT NULL COMMENT 'Text of hyperlink.',
  `type` varchar(30) DEFAULT NULL COMMENT 'Mime type of content (image/jpeg)'
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=latin1;

--
-- Dumping data for table link
--

INSERT INTO link (linkid, metid, perid, wptid, rteid, rptid, trkid, tptid, href, text, type) VALUES
(4, NULL, 2, NULL, NULL, NULL, NULL, NULL, 'http://www.nhspoi.com/gpx/vcard/simllc', 'NHSPOI GPX Author Link', 'text/x-vcard'),
(5, NULL, 3, NULL, NULL, NULL, NULL, NULL, 'http://www.nhspoi.com/blog', 'Site Blog Link', 'text/html'),
(6, 4, NULL, NULL, NULL, NULL, NULL, NULL, 'gpxservice/name/GPXTest.html', 'HTML View', 'text/html'),
(7, NULL, 4, NULL, NULL, NULL, NULL, NULL, 'http://www.geocaching.com/profile/?guid=471967b3-0ecf-42c3-b4c7-b312b54b8ae2', 'njdc24 Profile', 'null'),
(8, 5, NULL, NULL, NULL, NULL, NULL, NULL, 'http://www.geocaching.com', 'Geocaching - High Tech Treasure Hunting', ''),
(9, NULL, NULL, 19, NULL, NULL, NULL, NULL, 'http://www.geocaching.com/seek/cache_details.aspx?guid=a9be36e7-4420-4068-aa09-a5a732bb3d62', '"M"''s Happy Birthday Cache', 'Visit Link'),
(10, NULL, 5, NULL, NULL, NULL, NULL, NULL, 'http://www.geocaching.com/profile/?guid=471967b3-0ecf-42c3-b4c7-b312b54b8ae2', 'njdc24 Profile', 'null'),
(11, 6, NULL, NULL, NULL, NULL, NULL, NULL, 'http://www.geocaching.com', 'Geocaching - High Tech Treasure Hunting', ''),
(12, NULL, NULL, 20, NULL, NULL, NULL, NULL, 'http://www.geocaching.com/seek/cache_details.aspx?guid=eceec966-57db-4f83-8678-9d382a2ffda1', 'Peddler''s Pine', 'Visit Link'),
(13, NULL, 6, NULL, NULL, NULL, NULL, NULL, 'http://www.geocaching.com/profile/?guid=471967b3-0ecf-42c3-b4c7-b312b54b8ae2', 'njdc24 Profile', 'null'),
(14, 7, NULL, NULL, NULL, NULL, NULL, NULL, 'http://www.geocaching.com', 'Geocaching - High Tech Treasure Hunting', ''),
(15, NULL, NULL, 21, NULL, NULL, NULL, NULL, 'http://www.geocaching.com/seek/cache_details.aspx?guid=bbf77ce9-f3c5-48d5-8cee-08866e142ff8', 'KMT''S Perfect Picture Place', 'Visit Link'),
(16, NULL, 7, NULL, NULL, NULL, NULL, NULL, 'http://www.geocaching.com/profile/?guid=471967b3-0ecf-42c3-b4c7-b312b54b8ae2', 'njdc24 Profile', 'null'),
(17, 8, NULL, NULL, NULL, NULL, NULL, NULL, 'http://www.geocaching.com', 'Geocaching - High Tech Treasure Hunting', ''),
(18, NULL, NULL, 22, NULL, NULL, NULL, NULL, 'http://www.geocaching.com/seek/cache_details.aspx?guid=5f2421bc-7a67-43a9-a5e9-4055b3dcb204', 'A Toast to 10 years of Geocaching', 'Visit Link'),
(19, NULL, 8, NULL, NULL, NULL, NULL, NULL, 'http://www.nhspoi.com/blog', 'Site Blog Link', 'text/html'),
(20, 9, NULL, NULL, NULL, NULL, NULL, NULL, 'gpxservice/name/GPXTest.html', 'HTML View', 'text/html'),
(21, NULL, 9, NULL, NULL, NULL, NULL, NULL, 'http://www.nhspoi.com/gpx/vcard/simllc', 'NHSPOI GPX Author Link', 'text/x-vcard'),
(22, 11, NULL, NULL, NULL, NULL, NULL, NULL, 'http://www.garmin.com', 'Garmin International', 'null'),
(24, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://faculty.lebow.drexel.edu/McCainR//top/bridge/CBErwinna.HTML', 'More facts', 'text/html'),
(25, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://www.davidhanauer.com/buckscounty/coveredbridges/index.html', 'Site Links', 'text/html'),
(26, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://en.wikipedia.org/wiki/Erwinna_Covered_Bridge', 'Wikipedia', 'text/html'),
(27, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://www.davidhanauer.com/buckscounty/coveredbridges/index.html', 'Pictures', 'text/html'),
(28, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://en.wikipedia.org/wiki/File:Loux_Covered_Bridge,_Carversville-Wisner_Road_across_Cabin_Run,_Pipersville_%28Bucks_County,_Pennsylvania%29.jpg', 'Wikipedia', 'text/html'),
(29, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://en.wikipedia.org/wiki/Cabin_Run_Covered_Bridge', 'Wikipedia', 'text/html'),
(30, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://www.larryhillpot.com/frankenfield_bridge.htm', 'Larry Hillpots Site', 'text/html'),
(31, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://www.flickr.com/photos/32803556@N07/4997116653/in/photostream/', 'Kevin Nelsons Flickr post', 'text/html'),
(32, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://en.wikipedia.org/wiki/Van_Sant_Covered_Bridge', 'Wikipedia', 'text/html'),
(34, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://faculty.lebow.drexel.edu/McCainR//top/bridge/CBErwinna.HTML', 'More facts', 'text/html'),
(35, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://www.davidhanauer.com/buckscounty/coveredbridges/index.html', 'Site Links', 'text/html'),
(36, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://en.wikipedia.org/wiki/Erwinna_Covered_Bridge', 'Wikipedia', 'text/html'),
(37, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://www.davidhanauer.com/buckscounty/coveredbridges/index.html', 'Pictures', 'text/html'),
(38, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://en.wikipedia.org/wiki/File:Loux_Covered_Bridge,_Carversville-Wisner_Road_across_Cabin_Run,_Pipersville_%28Bucks_County,_Pennsylvania%29.jpg', 'Wikipedia', 'text/html'),
(39, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://en.wikipedia.org/wiki/Cabin_Run_Covered_Bridge', 'Wikipedia', 'text/html'),
(40, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://www.larryhillpot.com/frankenfield_bridge.htm', 'Larry Hillpots Site', 'text/html'),
(41, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://www.flickr.com/photos/32803556@N07/4997116653/in/photostream/', 'Kevin Nelsons Flickr post', 'text/html'),
(42, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://en.wikipedia.org/wiki/Van_Sant_Covered_Bridge', 'Wikipedia', 'text/html'),
(44, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://faculty.lebow.drexel.edu/McCainR//top/bridge/CBErwinna.HTML', 'More facts', 'text/html'),
(45, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://www.davidhanauer.com/buckscounty/coveredbridges/index.html', 'Site Links', 'text/html'),
(46, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://en.wikipedia.org/wiki/Erwinna_Covered_Bridge', 'Wikipedia', 'text/html'),
(47, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://www.davidhanauer.com/buckscounty/coveredbridges/index.html', 'Pictures', 'text/html'),
(48, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://en.wikipedia.org/wiki/File:Loux_Covered_Bridge,_Carversville-Wisner_Road_across_Cabin_Run,_Pipersville_%28Bucks_County,_Pennsylvania%29.jpg', 'Wikipedia', 'text/html'),
(49, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://en.wikipedia.org/wiki/Cabin_Run_Covered_Bridge', 'Wikipedia', 'text/html'),
(50, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://www.larryhillpot.com/frankenfield_bridge.htm', 'Larry Hillpots Site', 'text/html'),
(51, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://www.flickr.com/photos/32803556@N07/4997116653/in/photostream/', 'Kevin Nelsons Flickr post', 'text/html'),
(52, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'http://en.wikipedia.org/wiki/Van_Sant_Covered_Bridge', 'Wikipedia', 'text/html'),
(53, NULL, 14, NULL, NULL, NULL, NULL, NULL, 'http://www.nhspoi.com/gpx/vcard/simllc', 'NHSPOI GPX Author Link', 'text/x-vcard'),
(54, NULL, NULL, 66, NULL, NULL, NULL, NULL, 'http://faculty.lebow.drexel.edu/McCainR//top/bridge/CBErwinna.HTML', 'More facts', 'text/html'),
(55, NULL, NULL, 66, NULL, NULL, NULL, NULL, 'http://www.davidhanauer.com/buckscounty/coveredbridges/index.html', 'Site Links', 'text/html'),
(56, NULL, NULL, 66, NULL, NULL, NULL, NULL, 'http://en.wikipedia.org/wiki/Erwinna_Covered_Bridge', 'Wikipedia', 'text/html'),
(57, NULL, NULL, 67, NULL, NULL, NULL, NULL, 'http://www.davidhanauer.com/buckscounty/coveredbridges/index.html', 'Pictures', 'text/html'),
(58, NULL, NULL, 67, NULL, NULL, NULL, NULL, 'http://en.wikipedia.org/wiki/File:Loux_Covered_Bridge,_Carversville-Wisner_Road_across_Cabin_Run,_Pipersville_%28Bucks_County,_Pennsylvania%29.jpg', 'Wikipedia', 'text/html'),
(59, NULL, NULL, 68, NULL, NULL, NULL, NULL, 'http://en.wikipedia.org/wiki/Cabin_Run_Covered_Bridge', 'Wikipedia', 'text/html'),
(60, NULL, NULL, 69, NULL, NULL, NULL, NULL, 'http://www.larryhillpot.com/frankenfield_bridge.htm', 'Larry Hillpots Site', 'text/html'),
(61, NULL, NULL, 69, NULL, NULL, NULL, NULL, 'http://www.flickr.com/photos/32803556@N07/4997116653/in/photostream/', 'Kevin Nelsons Flickr post', 'text/html'),
(62, NULL, NULL, 77, NULL, NULL, NULL, NULL, 'http://en.wikipedia.org/wiki/Van_Sant_Covered_Bridge', 'Wikipedia', 'text/html');

-- --------------------------------------------------------

--
-- Table structure for table lov_link
--

CREATE TABLE lov_link (
  lovid int(11) NOT NULL,
  title varchar(60) DEFAULT NULL COMMENT 'Specifies extra information about an element.',
  lang varchar(60) DEFAULT NULL COMMENT 'Language code for the content in an element.',
  href varchar(200) DEFAULT NULL COMMENT 'Specifies the destination of a link',
  `text` varchar(100) DEFAULT NULL COMMENT 'Text of hyperlink.',
  `type` varchar(30) DEFAULT NULL COMMENT 'Mime type of content (image/jpeg)'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table lov_link
--

INSERT INTO lov_link (lovid, title, lang, href, text, type) VALUES
(1, 'This one guarantees your right to share', 'en', 'http://www.gnu.org/copyleft/gpl.html', 'GNU General Public License', 'type/html'),
(2, 'Attribution 3.0 Unported (CC BY 3.0) ', 'en', 'http://creativecommons.org/licenses/by/3.0/', 'Creative Commons', 'type/html'),
(3, 'Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0) ', 'en', 'http://creativecommons.org/licenses/by-sa/3.0/', 'Creative Commons', 'type/html'),
(4, 'Attribution-NonCommercial-ShareAlike 3.0 Unported (CC BY-NC-', 'en', 'http://creativecommons.org/licenses/by-nc-sa/3.0/', 'Creative Commons NC', 'type/html'),
(5, 'Attribution-NonCommercial-NoDerivs 3.0 Unported (CC BY-NC-ND', 'en', 'http://creativecommons.org/licenses/by-nc-nd/3.0/', 'Creative Commons (CC BY-NC-ND 3.0)', 'type/html');

-- --------------------------------------------------------

--
-- Table structure for table markers
--

CREATE TABLE markers (
  id int(11) NOT NULL,
  `name` varchar(60) NOT NULL,
  address varchar(80) NOT NULL,
  lat float(10,6) NOT NULL,
  lng float(10,6) NOT NULL,
  `type` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

--
-- Dumping data for table markers
--

INSERT INTO markers (id, name, address, lat, lng, type) VALUES
(1, 'Pan Africa Market', '1521 1st Ave, Seattle, WA', 47.608940, -122.340141, 'restaurant'),
(2, 'Buddha Thai & Bar', '2222 2nd Ave, Seattle, WA', 47.613590, -122.344391, 'bar'),
(3, 'The Melting Pot', '14 Mercer St, Seattle, WA', 47.624561, -122.356445, 'restaurant'),
(4, 'Ipanema Grill', '1225 1st Ave, Seattle, WA', 47.606365, -122.337654, 'restaurant'),
(5, 'Sake House', '2230 1st Ave, Seattle, WA', 47.612823, -122.345673, 'bar'),
(6, 'Crab Pot', '1301 Alaskan Way, Seattle, WA', 47.605961, -122.340363, 'restaurant'),
(7, 'Mama''s Mexican Kitchen', '2234 2nd Ave, Seattle, WA', 47.613976, -122.345467, 'bar'),
(8, 'Wingdome', '1416 E Olive Way, Seattle, WA', 47.617214, -122.326584, 'bar'),
(9, 'Piroshky Piroshky', '1908 Pike pl, Seattle, WA', 47.610126, -122.342834, 'restaurant'),
(21, 'New marker', 'New address', 10.006000, 40.000900, 'TestCreate'),
(23, 'New marker', 'New address', 21.006001, -40.900101, 'TestUpdate'),
(24, 'New marker', 'New address', 10.006000, 40.000900, 'TestCreate'),
(26, 'New marker', 'New address', 21.006001, -40.900101, 'TestUpdate'),
(27, 'New marker', 'New address', 10.006000, 40.000900, 'TestCreate'),
(29, 'New marker', 'New address', 21.006001, -40.900101, 'TestUpdate'),
(30, 'New marker', 'New address', 10.006000, 40.000900, 'TestCreate'),
(32, 'New marker', 'New address', 21.006001, -40.900101, 'TestUpdate'),
(33, 'New marker', 'New address', 10.006000, 40.000900, 'TestCreate'),
(35, 'New marker', 'New address', 21.006001, -40.900101, 'TestUpdate');

-- --------------------------------------------------------

--
-- Table structure for table metadata
--

CREATE TABLE metadata (
  metid int(11) NOT NULL,
  gpxid int(11) DEFAULT NULL,
  bndid int(11) DEFAULT NULL COMMENT 'Bounds FK',
  crtid int(11) DEFAULT NULL COMMENT 'Copyright FK',
  perid int(11) DEFAULT NULL COMMENT 'Person FK',
  extid int(11) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  `desc` varchar(2000) DEFAULT NULL,
  link varchar(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  keywords varchar(200) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table metadata
--

INSERT INTO metadata (metid, gpxid, bndid, crtid, perid, extid, name, `desc`, link, time, keywords) VALUES
(2, 2, NULL, NULL, NULL, NULL, 'NewHopeToCenterBridgeRide', 'Simple bike ride.', NULL, '2011-03-14 09:31:44', 'bike track'),
(3, 3, 2, NULL, 2, NULL, 'Solbury Township Border Rte', 'NHS POI Test With Way Points', NULL, '2011-02-23 15:15:07', 'Sample autogenerated gpx'),
(4, 4, 3, 1, 3, NULL, 'BedAndBreakfasts', 'Description goes here.', NULL, NULL, 'art history gallery'),
(5, 5, 4, 2, 4, NULL, 'GC1VPT7 "M"''s Happy Birthday Cache', 'This is an individual cache generated from Geocaching.com', NULL, '2011-03-27 17:13:33', 'cache, geocache'),
(6, 6, 5, 3, 5, NULL, 'GC1QHV9 Peddler''s Pine', 'This is an individual cache generated from Geocaching.com', NULL, '2011-03-27 17:13:39', 'cache, geocache'),
(7, 7, 6, 4, 6, NULL, 'GC1ZFJV KMT''S Perfect Picture Place', 'This is an individual cache generated from Geocaching.com', NULL, '2011-03-27 17:13:36', 'cache, geocache'),
(8, 8, 7, 5, 7, NULL, 'GC27Z8Q A Toast to 10 years of Geocaching', 'This is an individual cache generated from Geocaching.com', NULL, '2011-03-27 17:13:46', 'cache, geocache'),
(9, 9, 8, 6, 8, NULL, 'GPXTest From my ipad', 'Description goes here.Wept doo', NULL, NULL, 'art history gallery'),
(10, 10, 9, NULL, 9, NULL, 'Solbury Township Border Rte', 'NHS POI Test With Way Points', NULL, '2011-02-23 15:15:07', 'Sample autogenerated gpx'),
(11, 11, 10, NULL, NULL, NULL, NULL, NULL, NULL, '2009-02-04 07:59:35', NULL),
(12, 12, 11, NULL, 10, NULL, 'Cushetunk Trail', 'This is the first six miles of the Cushetunk Trail at the Round Valley Reservoir. It is a multi-use trail, Hiking, Bicycling and Horse Riding are allowed. I started from Campsite 79, went to the path that leads to the campsites, turned left to the Cushetunk connector, then right onto the Cushetuk Trail. To go left from the connector is closed from January till August, that direction is another 3 miles.', NULL, '2005-05-01 17:44:47', 'Lebanon, NJ, New Jersey, Round Valley, Round, Valley, reservoir'),
(16, 16, 15, NULL, 14, NULL, 'Bucks County Covered Bridges', 'NHS POI Way Points', NULL, NULL, 'Bridges');

-- --------------------------------------------------------

--
-- Table structure for table person
--

CREATE TABLE person (
  perid int(11) NOT NULL,
  metid int(11) DEFAULT NULL,
  emailid int(11) DEFAULT NULL COMMENT 'Link to email table',
  linkid int(11) DEFAULT NULL COMMENT 'Link to Web site or other external information about person.',
  `name` varchar(60) DEFAULT NULL COMMENT 'Name of person or organization.'
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table person
--

INSERT INTO person (perid, metid, emailid, linkid, name) VALUES
(2, 3, 2, 4, 'Chris Pauley'),
(3, 4, 3, 5, 'Chris Pauley'),
(4, 5, NULL, 7, 'njdc24'),
(5, 6, NULL, 10, 'njdc24'),
(6, 7, NULL, 13, 'njdc24'),
(7, 8, NULL, 16, 'njdc24'),
(8, 9, 4, 19, 'Chris Pauley'),
(9, 10, 5, 21, 'Chris Pauley'),
(10, 12, 6, NULL, 'Rober Carinci'),
(14, 16, 10, 53, 'Chris Pauley');

-- --------------------------------------------------------

--
-- Table structure for table rte
--

CREATE TABLE rte (
  rteid int(11) NOT NULL,
  gpxid int(11) DEFAULT NULL,
  extid int(11) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  cmt varchar(60) DEFAULT NULL COMMENT 'comments',
  `desc` varchar(500) DEFAULT NULL,
  number int(11) DEFAULT NULL COMMENT 'non negative integer',
  `type` varchar(30) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table rte
--

INSERT INTO rte (rteid, gpxid, extid, name, cmt, `desc`, number, type) VALUES
(1, 3, NULL, 'Route along the township border', NULL, NULL, 0, NULL),
(2, 10, NULL, 'Route along the township border', NULL, NULL, 0, NULL),
(3, 11, NULL, 'Beginning 2', NULL, NULL, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table rtept
--

CREATE TABLE rtept (
  rptid int(11) NOT NULL,
  gpxid int(11) DEFAULT NULL,
  rteid int(11) DEFAULT NULL,
  extid int(11) DEFAULT NULL,
  lat float(10,6) NOT NULL,
  lon float(10,6) NOT NULL,
  ele float(10,6) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  magvar float(3,1) DEFAULT NULL,
  geoidheight float(6,1) DEFAULT NULL,
  `name` varchar(120) DEFAULT NULL,
  cmt varchar(120) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `desc` varchar(2000) DEFAULT NULL,
  src varchar(60) DEFAULT NULL,
  sym varchar(30) DEFAULT NULL,
  fix varchar(4) DEFAULT NULL,
  sat int(11) DEFAULT NULL,
  hdop float DEFAULT NULL,
  vdop float DEFAULT NULL,
  pdop float DEFAULT NULL,
  ageofdgpsdata float DEFAULT NULL,
  dgpsid int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table rtept
--

INSERT INTO rtept (rptid, gpxid, rteid, extid, lat, lon, ele, time, magvar, geoidheight, name, cmt, type, `desc`, src, sym, fix, sat, hdop, vdop, pdop, ageofdgpsdata, dgpsid) VALUES
(1, NULL, 1, NULL, 40.408920, -75.043320, NULL, '2011-02-23 15:15:07', NULL, NULL, 'Lumberville Corner', '', 'boundary', 'Northern corner of the township.', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(2, NULL, 2, NULL, 40.408920, -75.043320, NULL, '2011-02-23 15:15:07', NULL, NULL, 'Lumberville Corner', '', 'boundary', 'Northern corner of the township.', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(3, NULL, 3, NULL, 39.600754, -75.745857, NULL, '2008-06-04 07:27:06', NULL, NULL, 'Safeway', 'Peoples Plz', NULL, 'Peoples Plz', NULL, 'Parking Area', NULL, 0, NULL, NULL, NULL, NULL, 0),
(4, NULL, 3, NULL, 39.589905, -75.741936, NULL, '2008-06-04 08:10:12', NULL, NULL, '001', '2414 Old County Rd', NULL, '2414 Old County Rd', NULL, 'Waypoint', NULL, 0, NULL, NULL, NULL, NULL, 0),
(5, NULL, 3, NULL, 39.556561, -75.832947, NULL, '2008-06-04 08:10:29', NULL, NULL, '006', '105 McKeown Rd', NULL, '105 McKeown Rd', NULL, 'Waypoint', NULL, 0, NULL, NULL, NULL, NULL, 0),
(6, NULL, 3, NULL, 39.548195, -75.829178, NULL, '2008-06-04 08:10:31', NULL, NULL, '015', '682 Elk Forest Rd', NULL, '682 Elk Forest Rd', NULL, 'Waypoint', NULL, 0, NULL, NULL, NULL, NULL, 0),
(7, NULL, 3, NULL, 39.551491, -75.808754, NULL, '2008-06-04 08:10:35', NULL, NULL, '019', '12 Spears Hill Rd', NULL, '12 Spears Hill Rd', NULL, 'Waypoint', NULL, 0, NULL, NULL, NULL, NULL, 0),
(8, NULL, 3, NULL, 39.536644, -75.808914, NULL, '2008-06-04 08:10:44', NULL, NULL, '023', 'Lock St', NULL, 'Lock St', NULL, 'Waypoint', NULL, 0, NULL, NULL, NULL, NULL, 0),
(9, NULL, 3, NULL, 39.530251, -75.810760, NULL, '2008-06-04 08:10:59', NULL, NULL, '$Rest Stop', '155 Lock St', NULL, '155 Lock St', NULL, 'Swimming Area', NULL, 0, NULL, NULL, NULL, NULL, 0),
(10, NULL, 3, NULL, 39.548084, -75.747437, NULL, '2008-06-04 08:12:16', NULL, NULL, '036', 'Road', NULL, 'Road', NULL, 'Waypoint', NULL, 0, NULL, NULL, NULL, NULL, 0),
(11, NULL, 3, NULL, 39.549320, -75.742905, NULL, '2008-06-04 08:12:22', NULL, NULL, '$Veterans Memorial', 'Road', NULL, 'Road', NULL, 'Cover', NULL, 0, NULL, NULL, NULL, NULL, 0),
(12, NULL, 3, NULL, 39.550720, -75.741623, NULL, '2008-06-04 08:12:53', NULL, NULL, '051', 'Road', NULL, 'Road', NULL, 'Waypoint', NULL, 0, NULL, NULL, NULL, NULL, 0),
(13, NULL, 3, NULL, 39.547920, -75.746979, NULL, '2008-06-04 08:13:03', NULL, NULL, '054', 'Chesapeake City Rd', NULL, 'Chesapeake City Rd', NULL, 'Waypoint', NULL, 0, NULL, NULL, NULL, NULL, 0),
(14, NULL, 3, NULL, 39.549152, -75.730827, NULL, '2008-06-04 08:14:08', NULL, NULL, '059', '3688 Red Lion Rd', NULL, '3688 Red Lion Rd', NULL, 'Waypoint', NULL, 0, NULL, NULL, NULL, NULL, 0),
(15, NULL, 3, NULL, 39.570675, -75.697937, NULL, '2008-06-04 08:14:15', NULL, NULL, '063', '310 Howell School Rd', NULL, '310 Howell School Rd', NULL, 'Waypoint', NULL, 0, NULL, NULL, NULL, NULL, 0),
(16, NULL, 3, NULL, 39.571609, -75.739174, NULL, '2008-06-04 08:14:31', NULL, NULL, '069', 'Denny Rd', NULL, 'Denny Rd', NULL, 'Waypoint', NULL, 0, NULL, NULL, NULL, NULL, 0),
(17, NULL, 3, NULL, 39.574429, -75.764221, NULL, '2008-06-04 08:14:40', NULL, NULL, '076', '2993 Frazer Rd', NULL, '2993 Frazer Rd', NULL, 'Waypoint', NULL, 0, NULL, NULL, NULL, NULL, 0),
(18, NULL, 3, NULL, 39.594830, -75.773788, NULL, '2008-06-04 08:14:48', NULL, NULL, '082', '2993 Frenchtown Rd', NULL, '2993 Frenchtown Rd', NULL, 'Waypoint', NULL, 0, NULL, NULL, NULL, NULL, 0),
(19, NULL, 3, NULL, 39.603653, -75.755089, NULL, '2008-06-04 08:14:55', NULL, NULL, '087', '2592 Pulaski Hwy', NULL, '2592 Pulaski Hwy', NULL, 'Waypoint', NULL, 0, NULL, NULL, NULL, NULL, 0),
(20, NULL, 3, NULL, 39.600754, -75.745857, NULL, '2008-06-04 07:27:06', NULL, NULL, 'Safeway', 'Peoples Plz', NULL, 'Peoples Plz', NULL, 'Parking Area', NULL, 0, NULL, NULL, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table trk
--

CREATE TABLE trk (
  trkid int(11) NOT NULL,
  gpxid int(11) DEFAULT NULL,
  extid int(11) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  cmt varchar(60) DEFAULT NULL COMMENT 'comments',
  `desc` varchar(500) DEFAULT NULL,
  number int(11) DEFAULT NULL COMMENT 'non negative integer',
  `type` varchar(30) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table trk
--

INSERT INTO trk (trkid, gpxid, extid, name, cmt, `desc`, number, type) VALUES
(1, 2, NULL, NULL, NULL, 'Up and down the towpath', 0, NULL),
(2, 12, NULL, 'Cushetunk', NULL, NULL, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table trkpt
--

CREATE TABLE trkpt (
  tptid int(11) NOT NULL,
  tsid int(11) DEFAULT NULL,
  extid int(11) DEFAULT NULL,
  lat float(10,6) NOT NULL,
  lon float(10,6) NOT NULL,
  ele float(10,6) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  magvar float(3,1) DEFAULT NULL,
  geoidheight float(6,1) DEFAULT NULL,
  `name` varchar(120) DEFAULT NULL,
  cmt varchar(120) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `desc` varchar(2000) DEFAULT NULL,
  src varchar(60) DEFAULT NULL,
  sym varchar(30) DEFAULT NULL,
  fix varchar(4) DEFAULT NULL,
  sat int(11) DEFAULT NULL,
  hdop float DEFAULT NULL,
  vdop float DEFAULT NULL,
  pdop float DEFAULT NULL,
  ageofdgpsdata float DEFAULT NULL,
  dgpsid int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=342 DEFAULT CHARSET=latin1;

--
-- Dumping data for table trkpt
--

INSERT INTO trkpt (tptid, tsid, extid, lat, lon, ele, time, magvar, geoidheight, name, cmt, type, `desc`, src, sym, fix, sat, hdop, vdop, pdop, ageofdgpsdata, dgpsid) VALUES
(1, 1, NULL, 40.364330, -74.951820, 26.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(2, 1, NULL, 40.364368, -74.951500, 26.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(3, 1, NULL, 40.364880, -74.951637, 25.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(4, 1, NULL, 40.366020, -74.952042, 24.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(5, 1, NULL, 40.370571, -74.953880, 30.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(6, 1, NULL, 40.371151, -74.953812, 30.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(7, 1, NULL, 40.372849, -74.954681, 32.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(8, 1, NULL, 40.374359, -74.955353, 23.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(9, 1, NULL, 40.374870, -74.955650, 31.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(10, 1, NULL, 40.375099, -74.955978, 31.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(11, 1, NULL, 40.375259, -74.956551, 30.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(12, 1, NULL, 40.375511, -74.956749, 27.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(13, 1, NULL, 40.375729, -74.957474, 27.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(14, 1, NULL, 40.375771, -74.958611, 28.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(15, 1, NULL, 40.375992, -74.959251, 29.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(16, 1, NULL, 40.376438, -74.959808, 29.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(17, 1, NULL, 40.379189, -74.962280, 26.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(18, 1, NULL, 40.379539, -74.962883, 26.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(19, 1, NULL, 40.380219, -74.964371, 31.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(20, 1, NULL, 40.380562, -74.964760, 33.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(21, 1, NULL, 40.380939, -74.964912, 33.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(22, 1, NULL, 40.381882, -74.965080, 30.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(23, 1, NULL, 40.382240, -74.965080, 31.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(24, 1, NULL, 40.382568, -74.964981, 31.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(25, 1, NULL, 40.383179, -74.964630, 33.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(26, 1, NULL, 40.384048, -74.963943, 32.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(27, 1, NULL, 40.384899, -74.962784, 31.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(28, 1, NULL, 40.385109, -74.962242, 31.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(29, 1, NULL, 40.385540, -74.961990, 29.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(30, 1, NULL, 40.385899, -74.962082, 29.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(31, 1, NULL, 40.389160, -74.963997, 31.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(32, 1, NULL, 40.390511, -74.964943, 31.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(33, 1, NULL, 40.391621, -74.965973, 32.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(34, 1, NULL, 40.392872, -74.967369, 29.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(35, 1, NULL, 40.393211, -74.967796, 33.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(36, 1, NULL, 40.394089, -74.969200, 31.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(37, 1, NULL, 40.394958, -74.970016, 29.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(38, 1, NULL, 40.395500, -74.970741, 33.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(39, 1, NULL, 40.398949, -74.975990, 39.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(40, 1, NULL, 40.399220, -74.976479, 52.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(41, 1, NULL, 40.400509, -74.979500, 41.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(42, 1, NULL, 40.401340, -74.980324, 31.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(43, 1, NULL, 40.401772, -74.980171, 31.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(44, 1, NULL, 40.404362, -74.978539, 23.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(45, 1, NULL, 40.406059, -74.977402, 29.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(46, 1, NULL, 40.405361, -74.976028, 28.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(47, 1, NULL, 40.404060, -74.973259, 34.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(48, 1, NULL, 40.402760, -74.970001, 31.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(49, 1, NULL, 40.402500, -74.969414, 33.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(50, 1, NULL, 40.401310, -74.967079, 33.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(51, 1, NULL, 40.400970, -74.966187, 32.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(52, 1, NULL, 40.400459, -74.966003, 32.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(53, 1, NULL, 40.399792, -74.965172, 32.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(54, 1, NULL, 40.399212, -74.964546, 37.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(55, 1, NULL, 40.398029, -74.963577, 38.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(56, 1, NULL, 40.397301, -74.963081, 29.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(57, 1, NULL, 40.396271, -74.962532, 38.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(58, 1, NULL, 40.395241, -74.962067, 35.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(59, 1, NULL, 40.394428, -74.961601, 30.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(60, 1, NULL, 40.393780, -74.961357, 30.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(61, 1, NULL, 40.392200, -74.960907, 28.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(62, 1, NULL, 40.391750, -74.960732, 24.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(63, 1, NULL, 40.390171, -74.959938, 24.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(64, 1, NULL, 40.389542, -74.959549, 26.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(65, 1, NULL, 40.388401, -74.958504, 31.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(66, 1, NULL, 40.387421, -74.957718, 37.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(67, 1, NULL, 40.386520, -74.956886, 31.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(68, 1, NULL, 40.383942, -74.954117, 29.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(69, 1, NULL, 40.383419, -74.953339, 28.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(70, 1, NULL, 40.383060, -74.952698, 36.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(71, 1, NULL, 40.381161, -74.948967, 29.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(72, 1, NULL, 40.380730, -74.948303, 26.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(73, 1, NULL, 40.380199, -74.947708, 28.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(74, 1, NULL, 40.379440, -74.947220, 27.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(75, 1, NULL, 40.377121, -74.946487, 33.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(76, 1, NULL, 40.375778, -74.945717, 29.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(77, 1, NULL, 40.366089, -74.942841, 27.000000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(78, 2, NULL, 40.615940, -74.804108, 150.583618, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(79, 2, NULL, 40.615955, -74.804138, 153.948364, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(80, 2, NULL, 40.615959, -74.804146, 151.064209, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(81, 2, NULL, 40.615963, -74.804153, 152.986938, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(82, 2, NULL, 40.615963, -74.804153, 140.489868, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(83, 2, NULL, 40.616520, -74.803589, 156.832153, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(84, 2, NULL, 40.616486, -74.803131, 152.986938, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(85, 2, NULL, 40.616489, -74.802795, 156.351562, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(86, 2, NULL, 40.616417, -74.802124, 163.080811, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(87, 2, NULL, 40.616619, -74.802208, 162.119507, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(88, 2, NULL, 40.616688, -74.802307, 163.080811, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(89, 2, NULL, 40.616859, -74.801765, 167.887329, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(90, 2, NULL, 40.614975, -74.800735, 171.252075, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(91, 2, NULL, 40.613644, -74.801079, 164.042114, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(92, 2, NULL, 40.612751, -74.801109, 161.158203, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(93, 2, NULL, 40.612343, -74.800926, 161.158203, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(94, 2, NULL, 40.611202, -74.799675, 166.926025, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(95, 2, NULL, 40.611198, -74.799667, 166.926025, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(96, 2, NULL, 40.611301, -74.799164, 163.080811, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(97, 2, NULL, 40.610352, -74.798485, 166.926025, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(98, 2, NULL, 40.609592, -74.798477, 166.926025, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(99, 2, NULL, 40.608192, -74.798676, 153.467651, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(100, 2, NULL, 40.607883, -74.798027, 165.964722, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(101, 2, NULL, 40.606426, -74.797333, 190.958984, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(102, 2, NULL, 40.606277, -74.797302, 191.439575, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(103, 2, NULL, 40.605900, -74.797440, 196.246338, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(104, 2, NULL, 40.605785, -74.797455, 197.207520, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(105, 2, NULL, 40.605522, -74.797478, 200.091675, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(106, 2, NULL, 40.605415, -74.797348, 200.572266, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(107, 2, NULL, 40.605152, -74.797455, 196.726929, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(108, 2, NULL, 40.605042, -74.797630, 196.726929, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(109, 2, NULL, 40.602760, -74.799995, 168.368042, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(110, 2, NULL, 40.602692, -74.800133, 165.484131, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(111, 2, NULL, 40.602310, -74.800766, 152.506348, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(112, 2, NULL, 40.602287, -74.800797, 152.506348, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(113, 2, NULL, 40.602196, -74.801163, 145.777100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(114, 2, NULL, 40.601788, -74.802948, 144.335205, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(115, 2, NULL, 40.601646, -74.803055, 144.815796, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(116, 2, NULL, 40.601475, -74.803185, 147.699707, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(117, 2, NULL, 40.601227, -74.803452, 150.583618, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(118, 2, NULL, 40.599514, -74.805870, 160.677490, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(119, 2, NULL, 40.598541, -74.808434, 168.368042, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(120, 2, NULL, 40.598354, -74.808907, 171.732666, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(121, 2, NULL, 40.598362, -74.808853, 170.771362, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(122, 2, NULL, 40.598385, -74.808861, 171.732666, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(123, 2, NULL, 40.598438, -74.808937, 172.213257, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(124, 2, NULL, 40.598820, -74.809303, 170.290649, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(125, 2, NULL, 40.598679, -74.809128, 171.252075, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(126, 2, NULL, 40.598373, -74.808975, 172.213257, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(127, 2, NULL, 40.598427, -74.809013, 171.732666, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(128, 2, NULL, 40.598404, -74.808998, 170.771362, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(129, 2, NULL, 40.598038, -74.809547, 169.810059, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(130, 2, NULL, 40.597916, -74.809784, 167.887329, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(131, 2, NULL, 40.597767, -74.810135, 166.926025, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(132, 2, NULL, 40.597591, -74.810654, 173.174683, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(133, 2, NULL, 40.597366, -74.811256, 182.307129, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(134, 2, NULL, 40.597149, -74.811729, 192.401123, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(135, 2, NULL, 40.596813, -74.812218, 201.052856, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(136, 2, NULL, 40.596786, -74.812386, 202.975464, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(137, 2, NULL, 40.596752, -74.812546, 202.494873, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(138, 2, NULL, 40.596745, -74.812584, 202.014282, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(139, 2, NULL, 40.596775, -74.812675, 201.533569, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(140, 2, NULL, 40.596733, -74.812920, 202.014282, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(141, 2, NULL, 40.596565, -74.813133, 202.975464, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(142, 2, NULL, 40.596447, -74.813530, 202.975464, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(143, 2, NULL, 40.596375, -74.813736, 202.975464, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(144, 2, NULL, 40.596313, -74.813927, 204.417480, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(145, 2, NULL, 40.596275, -74.814232, 203.936890, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(146, 2, NULL, 40.596279, -74.814255, 203.936890, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(147, 2, NULL, 40.596283, -74.814491, 206.820679, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(148, 2, NULL, 40.596268, -74.814690, 209.704834, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(149, 2, NULL, 40.596214, -74.814926, 212.588623, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(150, 2, NULL, 40.596119, -74.815269, 215.472656, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(151, 2, NULL, 40.595993, -74.815834, 218.837280, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(152, 2, NULL, 40.595993, -74.816109, 220.279175, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(153, 2, NULL, 40.596027, -74.816345, 221.721191, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(154, 2, NULL, 40.596035, -74.816490, 224.124512, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(155, 2, NULL, 40.596050, -74.816650, 226.527832, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(156, 2, NULL, 40.596062, -74.816879, 228.931152, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(157, 2, NULL, 40.596146, -74.817116, 231.334351, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(158, 2, NULL, 40.596268, -74.817535, 230.853760, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(159, 2, NULL, 40.596241, -74.817612, 230.853760, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(160, 2, NULL, 40.596142, -74.817940, 229.411743, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(161, 2, NULL, 40.596134, -74.818100, 227.969727, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(162, 2, NULL, 40.596149, -74.819023, 230.373047, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(163, 2, NULL, 40.596176, -74.819618, 233.737671, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(164, 2, NULL, 40.596169, -74.819687, 234.218384, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(165, 2, NULL, 40.596191, -74.819695, 235.179688, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(166, 2, NULL, 40.596169, -74.819801, 236.140991, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(167, 2, NULL, 40.596294, -74.820198, 237.582886, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(168, 2, NULL, 40.596416, -74.820709, 237.582886, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(169, 2, NULL, 40.596230, -74.821327, 237.582886, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(170, 2, NULL, 40.596165, -74.821472, 238.063599, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(171, 2, NULL, 40.596066, -74.821777, 239.986206, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(172, 2, NULL, 40.595989, -74.821930, 240.947632, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(173, 2, NULL, 40.596207, -74.822472, 239.505493, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(174, 2, NULL, 40.596424, -74.823074, 238.544312, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(175, 2, NULL, 40.596649, -74.823753, 238.063599, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(176, 2, NULL, 40.596703, -74.823807, 238.063599, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(177, 2, NULL, 40.597019, -74.824532, 245.754150, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(178, 2, NULL, 40.597267, -74.824516, 246.715454, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(179, 2, NULL, 40.597519, -74.824455, 247.196045, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(180, 2, NULL, 40.598480, -74.824142, 245.754150, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(181, 2, NULL, 40.598534, -74.824120, 245.754150, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(182, 2, NULL, 40.598568, -74.824135, 246.234863, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(183, 2, NULL, 40.598957, -74.824188, 244.312256, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(184, 2, NULL, 40.599033, -74.824203, 243.831543, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(185, 2, NULL, 40.599331, -74.824326, 242.870239, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(186, 2, NULL, 40.599579, -74.824440, 243.350830, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(187, 2, NULL, 40.599792, -74.824699, 247.196045, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(188, 2, NULL, 40.599876, -74.824905, 244.792847, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(189, 2, NULL, 40.600086, -74.825233, 243.350830, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(190, 2, NULL, 40.600182, -74.825600, 242.870239, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(191, 2, NULL, 40.600250, -74.826035, 241.908936, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(192, 2, NULL, 40.600399, -74.827133, 246.715454, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(193, 2, NULL, 40.600487, -74.827415, 247.676758, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(194, 2, NULL, 40.600704, -74.827805, 247.676758, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(195, 2, NULL, 40.600903, -74.828247, 247.676758, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(196, 2, NULL, 40.601162, -74.828613, 248.157593, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(197, 2, NULL, 40.601334, -74.828949, 248.157593, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(198, 2, NULL, 40.601433, -74.829346, 247.676758, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(199, 2, NULL, 40.601486, -74.829689, 244.792847, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(200, 2, NULL, 40.602051, -74.829933, 234.218384, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(201, 2, NULL, 40.602051, -74.829933, 234.218384, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(202, 2, NULL, 40.602131, -74.830162, 232.295776, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(203, 2, NULL, 40.602299, -74.830383, 228.450439, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(204, 2, NULL, 40.602436, -74.830704, 225.085938, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(205, 2, NULL, 40.602680, -74.831177, 218.356567, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(206, 2, NULL, 40.602840, -74.831490, 212.588623, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(207, 2, NULL, 40.602890, -74.831596, 211.146729, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(208, 2, NULL, 40.602898, -74.831932, 207.301392, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(209, 2, NULL, 40.602879, -74.832008, 206.820679, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(210, 2, NULL, 40.602840, -74.832413, 203.936890, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(211, 2, NULL, 40.602867, -74.833153, 195.765625, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(212, 2, NULL, 40.602875, -74.833336, 192.881714, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(213, 2, NULL, 40.602921, -74.833694, 188.075073, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(214, 2, NULL, 40.604145, -74.834801, 179.903809, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(215, 2, NULL, 40.604744, -74.834206, 182.787842, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(216, 2, NULL, 40.604759, -74.834259, 183.268433, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(217, 2, NULL, 40.604851, -74.834297, 179.423218, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(218, 2, NULL, 40.604855, -74.834419, 179.423218, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(219, 2, NULL, 40.605183, -74.834732, 175.577881, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(220, 2, NULL, 40.605431, -74.834999, 173.174683, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(221, 2, NULL, 40.605576, -74.835289, 172.213257, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(222, 2, NULL, 40.605850, -74.835533, 171.252075, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(223, 2, NULL, 40.606049, -74.835693, 170.290649, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(224, 2, NULL, 40.606808, -74.837196, 163.080811, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(225, 2, NULL, 40.606651, -74.837570, 160.196899, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(226, 2, NULL, 40.606815, -74.838242, 158.754761, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(227, 2, NULL, 40.607121, -74.838661, 152.506348, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(228, 2, NULL, 40.607388, -74.839066, 146.257812, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(229, 2, NULL, 40.606865, -74.839828, 140.489868, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(230, 2, NULL, 40.606632, -74.839775, 137.605835, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(231, 2, NULL, 40.606449, -74.839890, 134.241211, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(232, 2, NULL, 40.606102, -74.840378, 128.473389, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(233, 2, NULL, 40.605679, -74.841431, 119.821411, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(234, 2, NULL, 40.605755, -74.841843, 118.860107, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(235, 2, NULL, 40.606091, -74.841866, 117.898804, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(236, 2, NULL, 40.606728, -74.842117, 114.053589, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(237, 2, NULL, 40.607204, -74.842499, 110.688843, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(238, 2, NULL, 40.607384, -74.842712, 107.805054, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(239, 2, NULL, 40.607555, -74.842903, 106.843628, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(240, 2, NULL, 40.607616, -74.843170, 103.959717, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(241, 2, NULL, 40.607574, -74.843506, 102.037109, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(242, 2, NULL, 40.607555, -74.843529, 101.556396, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(243, 2, NULL, 40.607590, -74.843803, 100.114502, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(244, 2, NULL, 40.607601, -74.844025, 99.153076, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(245, 2, NULL, 40.607693, -74.844482, 94.827148, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(246, 2, NULL, 40.607616, -74.844696, 91.462524, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(247, 2, NULL, 40.607533, -74.845192, 89.539917, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(248, 2, NULL, 40.607540, -74.845451, 89.539917, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(249, 2, NULL, 40.607758, -74.845871, 89.539917, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(250, 2, NULL, 40.607769, -74.846191, 89.539917, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(251, 2, NULL, 40.607746, -74.846977, 85.213989, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(252, 2, NULL, 40.607670, -74.847435, 82.330078, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(253, 2, NULL, 40.607655, -74.847664, 78.965454, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(254, 2, NULL, 40.607616, -74.847878, 77.042847, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(255, 2, NULL, 40.607700, -74.847961, 73.678223, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(256, 2, NULL, 40.607731, -74.848030, 72.236206, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(257, 2, NULL, 40.608120, -74.848007, 72.716919, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(258, 2, NULL, 40.608238, -74.847946, 75.600830, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(259, 2, NULL, 40.608284, -74.847923, 75.600830, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(260, 2, NULL, 40.608509, -74.847946, 75.600830, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(261, 2, NULL, 40.608646, -74.847969, 75.120239, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(262, 2, NULL, 40.608883, -74.848129, 74.639526, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(263, 2, NULL, 40.609085, -74.848473, 78.004028, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(264, 2, NULL, 40.609192, -74.848679, 80.407471, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(265, 2, NULL, 40.609287, -74.848808, 83.771973, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(266, 2, NULL, 40.609409, -74.848991, 84.733398, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(267, 2, NULL, 40.609516, -74.849159, 87.136597, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(268, 2, NULL, 40.609615, -74.849304, 88.098022, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(269, 2, NULL, 40.609638, -74.849358, 88.098022, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(270, 2, NULL, 40.609718, -74.849564, 90.501343, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(271, 2, NULL, 40.609943, -74.849869, 94.346558, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(272, 2, NULL, 40.610699, -74.851715, 125.589355, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(273, 2, NULL, 40.610748, -74.851784, 124.628052, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(274, 2, NULL, 40.610855, -74.852074, 129.434692, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(275, 2, NULL, 40.611034, -74.852356, 135.683105, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(276, 2, NULL, 40.611168, -74.852417, 139.047852, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(277, 2, NULL, 40.611252, -74.852493, 141.451050, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(278, 2, NULL, 40.611340, -74.852577, 144.335205, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(279, 2, NULL, 40.612015, -74.853226, 155.870972, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(280, 2, NULL, 40.612263, -74.853531, 156.351562, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(281, 2, NULL, 40.612564, -74.853897, 154.909546, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(282, 2, NULL, 40.613060, -74.855103, 159.235474, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(283, 2, NULL, 40.613422, -74.855896, 151.544922, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(284, 2, NULL, 40.613560, -74.856857, 144.815796, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(285, 2, NULL, 40.613564, -74.856918, 146.257812, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(286, 2, NULL, 40.613605, -74.857201, 145.296387, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(287, 2, NULL, 40.613815, -74.857422, 141.931763, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(288, 2, NULL, 40.613968, -74.857658, 140.489868, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(289, 2, NULL, 40.614288, -74.858063, 134.241211, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(290, 2, NULL, 40.615654, -74.859856, 140.489868, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(291, 2, NULL, 40.617020, -74.859459, 141.451050, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(292, 2, NULL, 40.617165, -74.859406, 142.412476, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(293, 2, NULL, 40.617237, -74.859276, 142.412476, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(294, 2, NULL, 40.617252, -74.858589, 143.373657, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(295, 2, NULL, 40.617329, -74.858330, 141.451050, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(296, 2, NULL, 40.617340, -74.858047, 142.893066, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(297, 2, NULL, 40.617355, -74.857887, 146.257812, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(298, 2, NULL, 40.617355, -74.857574, 148.180420, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(299, 2, NULL, 40.617348, -74.856888, 149.622314, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(300, 2, NULL, 40.617352, -74.856636, 150.103027, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(301, 2, NULL, 40.617313, -74.855965, 152.025757, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(302, 2, NULL, 40.617226, -74.855721, 150.103027, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(303, 2, NULL, 40.617153, -74.855362, 148.180420, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(304, 2, NULL, 40.617058, -74.855087, 148.661011, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(305, 2, NULL, 40.616974, -74.854759, 147.218994, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(306, 2, NULL, 40.616982, -74.854454, 145.777100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(307, 2, NULL, 40.617088, -74.853897, 140.970459, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(308, 2, NULL, 40.617088, -74.853851, 140.489868, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(309, 2, NULL, 40.617123, -74.853546, 137.125244, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(310, 2, NULL, 40.617134, -74.853287, 134.241211, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(311, 2, NULL, 40.617153, -74.853188, 132.799316, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(312, 2, NULL, 40.617489, -74.852821, 126.550659, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(313, 2, NULL, 40.617519, -74.852722, 125.589355, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(314, 2, NULL, 40.617664, -74.852379, 121.744141, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(315, 2, NULL, 40.617722, -74.852081, 120.782837, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(316, 2, NULL, 40.617779, -74.851822, 120.782837, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(317, 2, NULL, 40.617935, -74.851768, 120.782837, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(318, 2, NULL, 40.618389, -74.851784, 125.108765, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(319, 2, NULL, 40.618530, -74.851883, 127.992554, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(320, 2, NULL, 40.619076, -74.852158, 135.683105, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(321, 2, NULL, 40.619194, -74.852272, 136.163940, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(322, 2, NULL, 40.619274, -74.852280, 136.163940, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(323, 2, NULL, 40.619789, -74.851761, 139.047852, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(324, 2, NULL, 40.619911, -74.851639, 141.931763, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(325, 2, NULL, 40.620060, -74.851265, 140.009155, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(326, 2, NULL, 40.620068, -74.851212, 139.047852, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(327, 2, NULL, 40.620094, -74.851067, 138.086548, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(328, 2, NULL, 40.620193, -74.850815, 136.644653, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(329, 2, NULL, 40.620319, -74.850533, 133.279907, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(330, 2, NULL, 40.620380, -74.850266, 132.799316, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(331, 2, NULL, 40.620388, -74.849953, 133.279907, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(332, 2, NULL, 40.620262, -74.849747, 133.279907, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(333, 2, NULL, 40.620174, -74.849648, 131.357300, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(334, 2, NULL, 40.620014, -74.849457, 130.395996, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(335, 2, NULL, 40.619877, -74.849274, 127.031372, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(336, 2, NULL, 40.619713, -74.849129, 124.628052, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(337, 2, NULL, 40.619537, -74.849052, 124.147339, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(338, 2, NULL, 40.619537, -74.849052, 123.186157, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(339, 2, NULL, 40.619556, -74.849083, 123.186157, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(340, 2, NULL, 40.619549, -74.849091, 122.705444, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(341, 2, NULL, 40.619549, -74.849091, 117.898804, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table trkseg
--

CREATE TABLE trkseg (
  tsid int(11) NOT NULL,
  trkid int(11) DEFAULT NULL,
  seqnum int(11) DEFAULT NULL,
  extid int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table trkseg
--

INSERT INTO trkseg (tsid, trkid, seqnum, extid) VALUES
(1, 1, 0, NULL),
(2, 2, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table user
--

CREATE TABLE `user` (
  userid int(11) NOT NULL,
  email varchar(255) NOT NULL,
  username varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  temp_pass varchar(255) DEFAULT NULL,
  temp_pass_active tinyint(1) NOT NULL DEFAULT '0',
  active int(11) NOT NULL DEFAULT '0',
  level_access int(11) NOT NULL DEFAULT '2',
  `hash` varchar(255) DEFAULT NULL,
  salt varchar(255) DEFAULT NULL,
  shared_secret varchar(255) DEFAULT NULL,
  created_date datetime DEFAULT NULL,
  last_login datetime DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

--
-- Dumping data for table user
--

INSERT INTO user (userid, email, username, password, temp_pass, temp_pass_active, active, level_access, hash, salt, shared_secret, created_date, last_login) VALUES
(1, 'chris@soleburyinternetmarketing.com', 'chris', '$2a$10$0LeVVWd2aetoKkIM1JUTG.O2/1KxzXbKnWNVK136Si9ge1o.o6LwG', NULL, 0, 1, 1, NULL, NULL, NULL, '2012-08-30 10:48:25', '2012-08-30 10:48:30'),
(2, 'user2@gpxdb.com', 'user2', 'password', 'password_temp', 1, 0, 0, 'hashvalue', 'saltvalue', NULL, '2012-08-30 16:50:05', NULL),
(3, 'user3@gpxdb.com', 'user3', 'password', 'password_temp', 1, 0, 0, 'hashvalue', 'saltvalue', NULL, '2012-08-30 16:50:17', NULL),
(4, 'user4@gpxdb.com', 'user4', 'password', 'password_temp', 1, 0, 0, 'hashvalue', 'saltvalue', NULL, '2012-08-30 16:58:33', NULL),
(5, 'user5@gpxdb.com', 'user5', 'password', 'password_temp', 1, 0, 0, 'hashvalue', 'saltvalue', NULL, '2012-08-30 16:59:57', NULL),
(6, 'user6@gpxdb.com', 'user6', 'password', NULL, 0, 1, 1, 'hashvalue', 'saltvalue', NULL, '2012-08-30 17:01:01', NULL),
(12, 'two@non.com', 'two', '$2a$10$.bv6TE3YK97bBnoJnNjDleLIwdFwSEDA9nv0603tdhXzsvjI3tvGC', NULL, 0, 1, 1, NULL, NULL, NULL, '2012-09-05 11:36:38', NULL),
(13, 'three@non.com', 'three', '$2a$10$QE6f7ZPfAIEnlTlQbusy0u64tsigATSeUhQE8/uXN5rM7YDdvw5x6', NULL, 0, 0, 2, NULL, NULL, NULL, '2012-09-05 11:46:14', NULL),
(26, 'joe@nhspoi.com', NULL, '', '$2a$10$K4wvlp.AAC7SSbk6VqVOJeRqsSGZppUrdC.shtQwZ14eoP0EBG0B2', 0, 0, 2, NULL, NULL, NULL, '2012-09-10 14:40:31', NULL),
(41, 'pauleyc@soleburyinternetmarketing.com', NULL, '$2a$10$utvhsgO76Whyqx0ktwS4r.F1cdy0wZIqEPSClaQeCiFRnE4lPnEIG', '$2a$10$uzxHkRXr9JDOoENCKK2Z9uleyf7jeLJ0HAirTwXUny/f0Orp9t7Ca', 0, 0, 2, NULL, NULL, NULL, '2012-10-01 17:03:01', '2012-10-04 16:27:38'),
(43, 'test_user@sample.com', 'test_user', 'password', NULL, 0, 0, 2, NULL, NULL, '8fd3373a-0d85-11e2-81c0-f428e60c8782', '2012-10-03 14:10:06', NULL),
(48, 'pauleyc@yahoo.com', NULL, '$2a$10$jwIMBtTRmSLk6cV1fvjTr.oreTB8P98y2zRR3YUDgOClV1CSs0vb2', NULL, 0, 1, 1, NULL, NULL, '209f8a14-0e3c-11e2-95ea-2807ab47600d', '2012-10-04 10:46:50', '2012-10-11 13:05:37'),
(49, 'pauleyc@comcast.net', NULL, '', '$2a$10$WIYSPw87iv2cU2tyjO58gegPuv7dy6RV5vnAv.lygWK5WMrcvztc2', 0, 0, 2, NULL, NULL, NULL, '2012-10-04 16:17:27', NULL),
(50, 'chris@soleburinternetmarketing.com', NULL, '$2a$10$JYxxkVLsgDEhrZ2XcI5F5.tbKOLf86yX8F28R7N/kAnhmh6y3zPR.', NULL, 0, 1, 1, NULL, NULL, 'a6e9281c-0e61-11e2-9554-000c29c8f4f7', '2012-10-04 16:24:19', '2012-10-04 16:25:45');

-- --------------------------------------------------------

--
-- Table structure for table user_group
--

CREATE TABLE user_group (
  groupid int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  description varchar(70) DEFAULT NULL,
  created datetime DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table user_group
--

INSERT INTO user_group (groupid, name, description, created) VALUES
(14, 'web_user', 'Unauthenticated or unauthorized users.', '2012-09-05 08:53:55'),
(17, 'admin', 'Administrator for application tasks.', '2012-09-05 08:54:54'),
(18, 'sysadmin', 'Responsible for application configuration tasks.', '2012-09-05 08:55:08'),
(19, 'registered_user', 'Previously authenticated users', '2012-09-05 09:03:11');

-- --------------------------------------------------------

--
-- Table structure for table user_group_members
--

CREATE TABLE user_group_members (
  gmid int(11) NOT NULL,
  groupid int(11) NOT NULL,
  userid int(11) NOT NULL,
  created datetime DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table user_group_members
--

INSERT INTO user_group_members (gmid, groupid, userid, created) VALUES
(1, 18, 1, '2012-09-05 09:08:23'),
(2, 17, 2, '2012-09-05 09:08:44'),
(3, 19, 3, '2012-09-05 09:09:22'),
(4, 19, 4, '2012-09-05 09:09:34'),
(5, 14, 5, '2012-09-05 09:09:51'),
(6, 14, 6, '2012-09-05 09:10:06'),
(7, 19, 46, NULL),
(8, 19, 48, NULL),
(9, 19, 50, NULL);

-- --------------------------------------------------------

--
-- Table structure for table user_login_token
--

CREATE TABLE user_login_token (
  id int(9) NOT NULL COMMENT 'pk',
  token varchar(36) NOT NULL COMMENT 'uuid',
  userid int(9) NOT NULL COMMENT 'fk to user.id',
  set_time datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table user_login_token
--

INSERT INTO user_login_token (id, token, userid, set_time) VALUES
(8, 'token1', 1, '2012-10-03 13:09:49'),
(9, '7351cfd0-0d90-11e2-81c0-f428e60c8782', 46, '2012-10-03 15:28:03'),
(10, '4a56806c-0e21-11e2-95ea-2807ab47600d', 46, '2012-10-04 08:44:51'),
(11, '2a4599dc-0e3c-11e2-95ea-2807ab47600d', 48, '2012-10-04 11:57:14'),
(12, 'a6e9b138-0e61-11e2-9554-000c29c8f4f7', 50, '2012-10-04 16:25:34');

-- --------------------------------------------------------

--
-- Table structure for table user_role
--

CREATE TABLE user_role (
  roleid int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  description varchar(70) DEFAULT NULL,
  created datetime DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table user_role
--

INSERT INTO user_role (roleid, name, description, created) VALUES
(13, 'administrator', 'Controls access', '2012-09-05 08:59:30'),
(14, 'submitter', 'Adds content', '2012-09-05 09:00:04'),
(15, 'editor', 'Updates on behalf of another', '2012-09-05 09:00:34'),
(16, 'consumer', 'Views content', '2012-09-05 09:01:07'),
(17, 'anonymous', 'Anyone', '2012-09-05 09:01:42');

-- --------------------------------------------------------

--
-- Table structure for table user_role_members
--

CREATE TABLE user_role_members (
  rmid int(11) NOT NULL,
  roleid int(11) NOT NULL,
  groupid int(11) NOT NULL,
  created datetime DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table user_role_members
--

INSERT INTO user_role_members (rmid, roleid, groupid, created) VALUES
(1, 13, 18, '2012-09-05 09:12:05'),
(2, 14, 18, '2012-09-05 09:13:11'),
(3, 15, 18, '2012-09-05 09:13:49'),
(4, 16, 18, NULL),
(5, 17, 18, NULL),
(6, 13, 17, NULL),
(7, 14, 17, '2012-09-05 09:17:29'),
(8, 15, 17, '2012-09-05 09:18:05'),
(9, 14, 19, NULL),
(10, 15, 19, NULL),
(11, 16, 19, NULL),
(12, 16, 14, '2012-09-05 09:23:12');

-- --------------------------------------------------------

--
-- Table structure for table wpt
--

CREATE TABLE wpt (
  wptid int(11) NOT NULL,
  gpxid int(11) DEFAULT NULL,
  extid int(11) DEFAULT NULL,
  lat float(10,6) NOT NULL,
  lon float(10,6) NOT NULL,
  ele float(10,6) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  magvar float(3,1) DEFAULT NULL,
  geoidheight float(6,1) DEFAULT NULL,
  `name` varchar(120) DEFAULT NULL,
  cmt varchar(120) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `desc` varchar(2000) DEFAULT NULL,
  src varchar(60) DEFAULT NULL,
  sym varchar(30) DEFAULT NULL,
  fix varchar(4) DEFAULT NULL,
  sat int(11) DEFAULT NULL,
  hdop float DEFAULT NULL,
  vdop float DEFAULT NULL,
  pdop float DEFAULT NULL,
  ageofdgpsdata float DEFAULT NULL,
  dgpsid int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=latin1;

--
-- Dumping data for table wpt
--

INSERT INTO wpt (wptid, gpxid, extid, lat, lon, ele, time, magvar, geoidheight, name, cmt, type, `desc`, src, sym, fix, sat, hdop, vdop, pdop, ageofdgpsdata, dgpsid) VALUES
(15, 3, 1, -74.000000, 45.000000, 44.000000, NULL, NULL, NULL, 'TestName1.1', 'Comment goes here.', 'poi', 'Description goes here.', NULL, 'marker', NULL, 0, NULL, NULL, NULL, NULL, 0),
(18, 4, 4, 40.363926, -74.955963, 44.000000, NULL, NULL, NULL, 'Wedgwood Inn', 'Comment goes here.', 'poi', 'Description goes here.', 'GoogleMaps', 'marker', NULL, 0, NULL, NULL, NULL, NULL, 0),
(19, 5, NULL, 40.346401, -75.033371, 99.375443, '2009-07-10 02:00:00', NULL, NULL, 'GC1VPT7', NULL, 'Geocache|Traditional Cache', '"M"', 'GoogleMaps', 'Geocache', NULL, 0, NULL, NULL, NULL, NULL, 0),
(20, 6, NULL, 40.347301, -75.036880, NULL, '2009-04-25 02:00:00', NULL, NULL, 'GC1QHV9', NULL, 'Geocache|Traditional Cache', 'Peddler''s Pine by KMT,NJ, Traditional Cache (2/1.5)', NULL, 'Geocache', NULL, 0, NULL, NULL, NULL, NULL, 0),
(21, 7, NULL, 40.347805, -75.032265, 95.645126, '2009-10-04 02:00:00', NULL, NULL, 'GC1ZFJV', NULL, 'Geocache|Traditional Cache', 'KMT', 'GoogleMaps', 'Geocache', NULL, 0, NULL, NULL, NULL, NULL, 0),
(22, 8, NULL, 40.351383, -75.010567, NULL, '2010-05-03 02:00:00', NULL, NULL, 'GC27Z8Q', NULL, 'Geocache|Traditional Cache', 'A Toast to 10 years of Geocaching by KMT,NJ, Traditional Cache (1.5/1.5)', NULL, 'Geocache', NULL, 0, NULL, NULL, NULL, NULL, 0),
(24, 11, NULL, 39.658279, -75.563576, 2.687815, '2008-06-04 07:32:31', NULL, NULL, '$Battery Park', '61 Delaware St', NULL, '61 Delaware St', 'GoogleMaps', 'Bike Trail', NULL, 0, NULL, NULL, NULL, NULL, 0),
(25, 11, NULL, 39.578522, -75.588600, 1.250924, '2008-06-04 07:34:47', NULL, NULL, '$Ice Cream ', '59 Clinton St', NULL, 'Ice cream at 59 Clinton St', 'GoogleMaps', 'Restaurant', NULL, 0, NULL, NULL, NULL, NULL, 0),
(26, 11, NULL, 39.530251, -75.810760, NULL, '2008-06-04 08:10:59', NULL, NULL, '$Rest Stop', '155 Lock St', NULL, '155 Lock St', NULL, 'Swimming Area', NULL, 0, NULL, NULL, NULL, NULL, 0),
(27, 11, NULL, 39.549320, -75.742905, 22.850418, '2008-06-04 08:12:22', NULL, NULL, '$Veterans Memorial', 'Road', NULL, 'Road', 'GoogleMaps', 'Cover', NULL, 0, NULL, NULL, NULL, NULL, 0),
(28, 11, NULL, 39.600754, -75.745857, NULL, '2008-06-04 07:27:06', NULL, NULL, 'Safeway', 'Peoples Plz', NULL, 'Peoples Plz', NULL, 'Parking Area', NULL, 0, NULL, NULL, NULL, NULL, 0),
(29, 12, NULL, 40.615944, -74.804108, 152.095200, '2005-05-01 17:43:20', NULL, NULL, 'RVSI79', '01-MAY-05 8:12:12AM', NULL, 'This is Site 79, my starting point.', 'GoogleMaps', 'Camping', NULL, 0, NULL, NULL, NULL, NULL, 0),
(66, 16, NULL, 40.502312, -75.074425, 36.784786, NULL, NULL, NULL, 'Erwinna Covered Bridge', 'The Erwinna Covered Bridge is a wooden covered bridge that spans Swamp Creek.', NULL, 'Bucks County''s shortest covered bridge, at only 56 feet (17 m) long', 'GoogleMaps', NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(67, 16, NULL, 40.422935, -75.127663, 107.709641, NULL, NULL, NULL, 'Loux Covered Bridge', 'This bridge was built in 1874', NULL, NULL, 'GoogleMaps', 'bridge', NULL, 0, NULL, NULL, NULL, NULL, 0),
(68, 16, NULL, 40.432056, -75.112625, 78.942093, NULL, NULL, NULL, 'Cabin Run Covered Bridge', 'It was built in 1871 and is 82 feet long', NULL, NULL, 'GoogleMaps', 'bridge', NULL, 0, NULL, NULL, NULL, NULL, 0),
(69, 16, NULL, 40.475761, -75.099518, 43.261635, NULL, NULL, NULL, 'Frankenfield Covered Bridge', 'It was built in 1872 and is 130 feet long', NULL, NULL, 'GoogleMaps', 'bridge', NULL, 0, NULL, NULL, NULL, NULL, 0),
(71, 16, NULL, 40.381199, -75.271645, NULL, NULL, NULL, NULL, 'Mood''s Covered Bridge', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(72, 16, NULL, 40.305286, -75.187256, NULL, NULL, NULL, NULL, 'Pine Valley Covered Bridge', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(73, 16, NULL, 40.244419, -74.980728, 23.728107, NULL, NULL, NULL, 'Schofield Ford Covered Bridge', NULL, NULL, NULL, 'GoogleMaps', NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(74, 16, NULL, 40.455143, -75.278366, NULL, NULL, NULL, NULL, 'Sheard''s Mill Bridge', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(75, 16, NULL, 40.366688, -75.297340, NULL, NULL, NULL, NULL, 'South Perkasie Covered Bridge', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(76, 16, NULL, 40.525421, -75.073166, NULL, NULL, NULL, NULL, 'Uhlerstown Covered Bridge', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0),
(77, 16, NULL, 40.327038, -74.957825, 22.838606, '2011-03-11 12:31:41', NULL, NULL, 'Van Sant Covered Bridge', NULL, NULL, 'Built in 1870. Also known as Beaver Dam Bridge. It crosses Pidcock Creek. Height limit: 12 ft 6 in; width 15 ft. Length: 86 ft. Weight limit: 7 tons.', 'GoogleMaps', 'bridge', NULL, 0, NULL, NULL, NULL, NULL, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table bounds
--
ALTER TABLE bounds
  ADD PRIMARY KEY (bndid);

--
-- Indexes for table copyright
--
ALTER TABLE copyright
  ADD PRIMARY KEY (crtid);

--
-- Indexes for table email
--
ALTER TABLE email
  ADD PRIMARY KEY (emailid);

--
-- Indexes for table extension
--
ALTER TABLE extension
  ADD PRIMARY KEY (extid);

--
-- Indexes for table extns
--
ALTER TABLE extns
  ADD PRIMARY KEY (nspid);

--
-- Indexes for table gpx
--
ALTER TABLE gpx
  ADD PRIMARY KEY (gpxid),
  ADD KEY idx_gpx_gpxid (gpxid);

--
-- Indexes for table guestbook
--
ALTER TABLE guestbook
  ADD PRIMARY KEY (id);

--
-- Indexes for table gx_address
--
ALTER TABLE gx_address
  ADD PRIMARY KEY (aid);

--
-- Indexes for table gx_address_street
--
ALTER TABLE gx_address_street
  ADD PRIMARY KEY (asid);

--
-- Indexes for table gx_autoroutepoint
--
ALTER TABLE gx_autoroutepoint
  ADD PRIMARY KEY (arpid);

--
-- Indexes for table gx_categories
--
ALTER TABLE gx_categories
  ADD PRIMARY KEY (catid);

--
-- Indexes for table gx_category
--
ALTER TABLE gx_category
  ADD PRIMARY KEY (catid);

--
-- Indexes for table gx_phonenumber
--
ALTER TABLE gx_phonenumber
  ADD PRIMARY KEY (pnid);

--
-- Indexes for table gx_rptext
--
ALTER TABLE gx_rptext
  ADD PRIMARY KEY (rpeid);

--
-- Indexes for table gx_rteext
--
ALTER TABLE gx_rteext
  ADD PRIMARY KEY (reid);

--
-- Indexes for table gx_tptext
--
ALTER TABLE gx_tptext
  ADD PRIMARY KEY (tpeid);

--
-- Indexes for table gx_trkext
--
ALTER TABLE gx_trkext
  ADD PRIMARY KEY (tkeid);

--
-- Indexes for table gx_wptext
--
ALTER TABLE gx_wptext
  ADD PRIMARY KEY (wpeid);

--
-- Indexes for table keywords
--
ALTER TABLE keywords
  ADD PRIMARY KEY (kwid);

--
-- Indexes for table link
--
ALTER TABLE link
  ADD PRIMARY KEY (linkid);

--
-- Indexes for table lov_link
--
ALTER TABLE lov_link
  ADD PRIMARY KEY (lovid);

--
-- Indexes for table markers
--
ALTER TABLE markers
  ADD PRIMARY KEY (id);

--
-- Indexes for table metadata
--
ALTER TABLE metadata
  ADD PRIMARY KEY (metid);

--
-- Indexes for table person
--
ALTER TABLE person
  ADD PRIMARY KEY (perid);

--
-- Indexes for table rte
--
ALTER TABLE rte
  ADD PRIMARY KEY (rteid);

--
-- Indexes for table rtept
--
ALTER TABLE rtept
  ADD PRIMARY KEY (rptid);

--
-- Indexes for table trk
--
ALTER TABLE trk
  ADD PRIMARY KEY (trkid);

--
-- Indexes for table trkpt
--
ALTER TABLE trkpt
  ADD PRIMARY KEY (tptid);

--
-- Indexes for table trkseg
--
ALTER TABLE trkseg
  ADD PRIMARY KEY (tsid);

--
-- Indexes for table user
--
ALTER TABLE user
  ADD PRIMARY KEY (userid),
  ADD UNIQUE KEY email (email);

--
-- Indexes for table user_group
--
ALTER TABLE user_group
  ADD PRIMARY KEY (groupid),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table user_group_members
--
ALTER TABLE user_group_members
  ADD PRIMARY KEY (gmid),
  ADD UNIQUE KEY group_user (groupid,userid);

--
-- Indexes for table user_login_token
--
ALTER TABLE user_login_token
  ADD PRIMARY KEY (id),
  ADD KEY token (token,userid),
  ADD KEY userid (userid);

--
-- Indexes for table user_role
--
ALTER TABLE user_role
  ADD PRIMARY KEY (roleid),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table user_role_members
--
ALTER TABLE user_role_members
  ADD PRIMARY KEY (rmid),
  ADD UNIQUE KEY role_group (groupid,roleid);

--
-- Indexes for table wpt
--
ALTER TABLE wpt
  ADD PRIMARY KEY (wptid);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table bounds
--
ALTER TABLE bounds
  MODIFY bndid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table copyright
--
ALTER TABLE copyright
  MODIFY crtid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table email
--
ALTER TABLE email
  MODIFY emailid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table extension
--
ALTER TABLE extension
  MODIFY extid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table extns
--
ALTER TABLE extns
  MODIFY nspid int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table gpx
--
ALTER TABLE gpx
  MODIFY gpxid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table guestbook
--
ALTER TABLE guestbook
  MODIFY id int(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=58;
--
-- AUTO_INCREMENT for table gx_address
--
ALTER TABLE gx_address
  MODIFY aid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table gx_address_street
--
ALTER TABLE gx_address_street
  MODIFY asid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table gx_autoroutepoint
--
ALTER TABLE gx_autoroutepoint
  MODIFY arpid int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table gx_categories
--
ALTER TABLE gx_categories
  MODIFY catid int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table gx_category
--
ALTER TABLE gx_category
  MODIFY catid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table gx_phonenumber
--
ALTER TABLE gx_phonenumber
  MODIFY pnid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table gx_rptext
--
ALTER TABLE gx_rptext
  MODIFY rpeid int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table gx_rteext
--
ALTER TABLE gx_rteext
  MODIFY reid int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table gx_tptext
--
ALTER TABLE gx_tptext
  MODIFY tpeid int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table gx_trkext
--
ALTER TABLE gx_trkext
  MODIFY tkeid int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table gx_wptext
--
ALTER TABLE gx_wptext
  MODIFY wpeid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table keywords
--
ALTER TABLE keywords
  MODIFY kwid int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table link
--
ALTER TABLE link
  MODIFY linkid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=63;
--
-- AUTO_INCREMENT for table lov_link
--
ALTER TABLE lov_link
  MODIFY lovid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table markers
--
ALTER TABLE markers
  MODIFY id int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT for table metadata
--
ALTER TABLE metadata
  MODIFY metid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table person
--
ALTER TABLE person
  MODIFY perid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table rte
--
ALTER TABLE rte
  MODIFY rteid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table rtept
--
ALTER TABLE rtept
  MODIFY rptid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table trk
--
ALTER TABLE trk
  MODIFY trkid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table trkpt
--
ALTER TABLE trkpt
  MODIFY tptid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=342;
--
-- AUTO_INCREMENT for table trkseg
--
ALTER TABLE trkseg
  MODIFY tsid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table user
--
ALTER TABLE user
  MODIFY userid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=51;
--
-- AUTO_INCREMENT for table user_group
--
ALTER TABLE user_group
  MODIFY groupid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table user_group_members
--
ALTER TABLE user_group_members
  MODIFY gmid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table user_login_token
--
ALTER TABLE user_login_token
  MODIFY id int(9) NOT NULL AUTO_INCREMENT COMMENT 'pk',AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table user_role
--
ALTER TABLE user_role
  MODIFY roleid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table user_role_members
--
ALTER TABLE user_role_members
  MODIFY rmid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table wpt
--
ALTER TABLE wpt
  MODIFY wptid int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=78;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
