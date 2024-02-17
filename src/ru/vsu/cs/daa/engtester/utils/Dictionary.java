package ru.vsu.cs.daa.engtester.utils;

import java.util.LinkedList;

public class Dictionary {
	public static class Pair {
		public String word;
		public String definition;

		public Pair(String word, String definition) {
			this.word = word;
			this.definition = definition;
		}
	}

	public LinkedList<Pair> words = new LinkedList<>();

	public Dictionary(int empty){
	}
	public Dictionary() {
		words.add(new Pair("violin","скрипка"));
		words.add(new Pair("viola","альт"));
		words.add(new Pair("cello","виолончель"));
		words.add(new Pair("double bass","контрабас"));
		words.add(new Pair("guitar","гитара"));
		words.add(new Pair("bass guitar","бас-гитара"));
		words.add(new Pair("banjo","банджо"));
		words.add(new Pair("harp","арфа"));
		words.add(new Pair("lute","лютня"));
		words.add(new Pair("dombra","домбра"));
		words.add(new Pair("mandolin","мандолина"));
		words.add(new Pair("lyre","лира"));
		words.add(new Pair("octobass","отктобас"));
		words.add(new Pair("clavichord","клавикорд"));
		words.add(new Pair("harpsichord","клавесин"));
		words.add(new Pair("piano","фортепиано"));
		words.add(new Pair("upright piano","пианино"));
		words.add(new Pair("grand piano","рояль"));
		words.add(new Pair("pump organ","фисгармония"));
		words.add(new Pair("organ","орган"));
		words.add(new Pair("bayan/button accordion","баян"));
		words.add(new Pair("accordion","аккордион"));
		words.add(new Pair("virginal","вёрджинел"));
		words.add(new Pair("spinet","спинет"));
		words.add(new Pair("trumpet","труба"));
		words.add(new Pair("trombone","тромбон"));
		words.add(new Pair("flugelhorn","флюгельгорн"));
		words.add(new Pair("french horn","валторна"));
		words.add(new Pair("cornet","корнет"));
		words.add(new Pair("tuba","туба"));
		words.add(new Pair("flute","флейта"));
		words.add(new Pair("clarinet","кларнет"));
		words.add(new Pair("bassoon","фагот"));
		words.add(new Pair("oboe","гобой"));
		words.add(new Pair("piccolo","пикколо, малая флейта"));
		words.add(new Pair("saxophone","саксофон"));
		words.add(new Pair("triangle","треугольник"));
		words.add(new Pair("drums","барабаны"));
		words.add(new Pair("tambourine","тамбурин, бубен"));
		words.add(new Pair("cymbals","тарелки, кимвалы"));
		words.add(new Pair("xylophone","ксилофон"));
		words.add(new Pair("timpani","литавры"));
		words.add(new Pair("glockenspiel","металлофон/глокеншпиль"));
		words.add(new Pair("marimba","маримба"));
//		words.add(new Pair("affiliation",
//						   "a connection with a political party or religion, or with a larger organization (связь)"));
//
//		words.add(new Pair("nonet",
//						   "9"));
//
//		words.add(new Pair("roar of applause",
//						   "гром аплодисментов"));
//
//		words.add(new Pair("perpetual loop",
//						   "бесконечная петля"));
//
//		words.add(new Pair("fray",
//						   "1) износиться 2) get annoyed"));
//
//		words.add(new Pair("outlive",
//						   "to live longer than sb"));
//
//		words.add(new Pair("yield",
//						   "1) to produce sth 2) to stop resisting"));
//
//		words.add(new Pair("transcend",
//						   "to go further, rise above, or be more important or better than sth"));
//
//		words.add(new Pair("epitomize",
//						   "to be a perfect example of a quality or type of thing"));
//
//		words.add(new Pair("endure",
//						   "to suffer sth difficult, unpleasant, or painful"));
//
//		words.add(new Pair("imbue sth with sth",
//						   "to fill sth/s.o. with a particular feeling, quality, or idea"));
//
//		words.add(new Pair("hamper",
//						   "to prevent s.o. doing sth easily"));
//
//		words.add(new Pair("recuperate",
//						   "to get back your health, strength or energy after being ill, tired, injured, etc.;\n" +
//								   "recover (восстановить силы, поправиться)"));
//
//		words.add(new Pair("desolate",
//						   "empty and not attractive, with no people"));
//
//		words.add(new Pair("blistering",
//						   "extremely hot; blazing"));
//
//		words.add(new Pair("impeccable",
//						   "without mistakes or faults"));
//
//		words.add(new Pair("notable",
//						   "deserving to be noticed or to receive attention; important"));
//
//		words.add(new Pair("fallow",
//						   """
//								   1) (about land) dug or ploughed and not used for growing crops, esp. so that
//								   the quality of the land will improve (вспаханный под пар)
//								   2) (of a period of time) when very little is created or produced; not successful
//								   (пустой, безрезультатный)"""));
//
//		words.add(new Pair("unrivalled",
//						   "unequalled, better than any other"));
//
//		words.add(new Pair("trite",
//						   "banal, boring, unoriginal, expressed many times before"));
//
//		words.add(new Pair("stunning",
//						   "extremely attractive or impressive; surprising"));
//
//		words.add(new Pair("up-and-coming",
//						   "likely to become successful or popular"));
//
//		words.add(new Pair("consecutive",
//						   "following one after another in a continuous series"));
//
//		words.add(new Pair("raucous",
//						   "sounding loud and rough"));
//
//		words.add(new Pair("deafening",
//						   "very loud"));
//
//		words.add(new Pair("harrowing",
//						   "shocking, frightening, extremely upsetting because connected with suffering\n" +
//								   "(душераздирающий, ужасающий)"));
//
//		words.add(new Pair("distressing",
//						   "extremely upsetting and worrying, esp. because sb is suffering\n" +
//								   "(огорчительный)"));
//
//		words.add(new Pair("disconcerting",
//						   "making you feel worried, confused or embarrassed (приводящий в\n" +
//								   "замешательство, сбивающий с толку, обескураживающий)"));
//
//		words.add(new Pair("gruelling",
//						   "very difficult and making you very tired (изнурительный)"));
//
//		words.add(new Pair("strenuous",
//						   "needing great effort and energy (трудный, требующий усилий)"));
//
//		words.add(new Pair("daunting",
//						   "frightening in a way that makes you feel less confident\n" +
//								   "(обескураживающий)"));
//
//		words.add(new Pair("pristine",
//						   "fresh and clean, as if new, and in very good condition \n" +
//								   "(первозданный, как новый)"));
//
//		words.add(new Pair("gnarled",
//						   "rough and twisted with hard lumps (сучковатый, искривлённый)"));
//
//		words.add(new Pair("winding",
//						   "having a curving and twisting shape"));
//
//		words.add(new Pair("fanciful",
//						   "imagined rather than based on facts disapproving"));
//
//		words.add(new Pair("fragrant",
//						   "having a pleasant smell"));
//
//		words.add(new Pair("grizzled",
//						   "having hair that is grey or partly grey"));

//		words.add(new Pair("ostensibly",
//						   "according to what seems or is stated to be real or true, when this is perhaps\n" +
//								   "not the case (якобы)"));
//
//		words.add(new Pair("at its finest/best",
//						   "at the highest standard that can be achieved"));
//
//		words.add(new Pair("within walking distance",
//						   "в пределах шаговой доступности"));
//
//		words.add(new Pair("at odds with",
//						   "in disagreement"));
//
//		words.add(new Pair("at a standstill",
//						   "a condition in which all movement or activity has stopped"));
//
//		words.add(new Pair("be at large",
//						   "(of a dangerous person or animal) be on the loose; not captured; free"));
//
//		words.add(new Pair("at a loose end",
//						   "having nothing to do and not knowing what you want to do"));
//
//		words.add(new Pair("at a loss",
//						   "not knowing what to say or do"));
	}
}
